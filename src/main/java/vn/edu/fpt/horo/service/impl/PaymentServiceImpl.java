package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.admin.GetListWithDrawResponse;
import vn.edu.fpt.horo.dto.admin.PaymentInCompilationResponse;
import vn.edu.fpt.horo.dto.admin.PaymentOutCompilationResponse;
import vn.edu.fpt.horo.dto.admin.RequestListAdvisorResponse;
import vn.edu.fpt.horo.dto.response.payment.CreateWithDrawResponse;
import vn.edu.fpt.horo.dto.request.payment.IncreaseCoinRequest;
import vn.edu.fpt.horo.dto.request.payment.CreateWithDrawRequest;
import vn.edu.fpt.horo.dto.response.payment.TransactionResponse;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.PaymentMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.PaymentService;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentTransDetailRepository paymentTransDetailRepository;
    private final CoinRepository coinRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final PaymentMapper paymentMapper;
    private final AdvisorRepository advisorRepository;

    @Override
    public BigDecimal increaseCoin(String accountId, IncreaseCoinRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        try {
            Coin coin = new Coin();
            ExchangeRates rates = exchangeRateRepository.findFirstByBankCode(request.getBankCode());
            ExchangeRates ratesWithdraw = exchangeRateRepository.findFirstByBankCode("WITH_DRAW");
            BigDecimal coinTransAmount = request.getTransAmount().multiply(rates.getCoefficients()).divide(rates.getPerUnit()).setScale(2, RoundingMode.DOWN);
            if ("00".equals(request.getErrorCode())) {
                if (account.getCoin() != null) {
                    coin.setCoinId(account.getCoin().getCoinId());
                    BigDecimal oldAmount = account.getCoin().getAmount();
                    BigDecimal newAmount = oldAmount.add(coinTransAmount);
                    coin.setAmount(newAmount);
                    coinRepository.save(coin);
                } else {
                    coinRepository.save(coin);
                    coin.setAmount(coinTransAmount);
                    coin.setCreatedDate(request.getIssueDate());
                    coinRepository.save(coin);
                    account.setCoin(coin);
                    accountRepository.save(account);
                }
            }
            BigDecimal adminCollectAmount = coinTransAmount.multiply((rates.getPerUnit().subtract(ratesWithdraw.getPerUnit()))).divide(rates.getCoefficients());
            paymentTransDetailRepository.save(PaymentTransDetail.builder()
                    .accountPayment(account)
                    .errorCode(request.getErrorCode())
                    .errorMessage(request.getErrorMessage())
                    .transAmount(request.getTransAmount())
                    .createDate(request.getIssueDate() == null ? LocalDateTime.now() : request.getIssueDate())
                    .bankCode(request.getBankCode())
                    .endDate(request.getIssueDate())
                    .rates(rates)
                    .transType(0L)
                    .coinTransAmount(coinTransAmount)
                    .adminCollectAmount(adminCollectAmount)
                    .status("00".equals(request.getErrorCode()))
                    .build());
            return coin.getAmount();
        } catch (Exception ex) {
            log.error("Error when save payment trans detail to database: {}", ex.getMessage());
            throw new BusinessException("Can't save payment trans detail to database: " + ex.getMessage());
        }
    }

    @Override
    public CreateWithDrawResponse withdrawCoin(String accountId, CreateWithDrawRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        Advisor advisor = advisorRepository.findFirstByAccount_AccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not found"));
        if (!advisor.getStatus().equals(AdvisorStatus.ACTIVE)) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not active");
        }
        Coin coin = account.getCoin();
        if (coin.getAmount().compareTo(request.getCoinWithDraw()) < 0) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Not enough coins available");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        ExchangeRates rates = exchangeRateRepository.findFirstByBankCode("WITH_DRAW");
        PaymentTransDetail response = paymentTransDetailRepository.save(PaymentTransDetail.builder()
                .accountPayment(account)
                .transAmount(request.getCoinWithDraw().multiply(rates.getPerUnit()).divide(rates.getCoefficients()))
                .createDate(LocalDateTime.parse((request.getCreateDate() == null ? LocalDateTime.now().toString() : request.getCreateDate().toString()),formatter))
                .rates(rates)
                .adminCollectAmount(new BigDecimal(0))
                .bankCode(request.getBankCode())
                .userBankName(request.getUserBankName())
                .userBankNumber(request.getUserBankNumber())
                .status(null)
                .transType(1L)
                .coinTransAmount(request.getCoinWithDraw())
                .build());
        coin.setAmount(coin.getAmount().subtract(request.getCoinWithDraw()));
        coinRepository.save(coin);
        return CreateWithDrawResponse.builder()
                .paymentTransId(response.getId())
                .withDrawAmount(response.getTransAmount())
                .build();
    }

    @Override
    public Page<GetListWithDrawResponse> getListWithDraw(Pageable pageable, String search) {
        Page<PaymentTransDetail> paymentWithDraw = paymentTransDetailRepository.findAllByTransTypeAndUserBankNameContaining(1L, search, pageable);
        return paymentWithDraw
                .map(paymentMapper::getWithDrawMapper);
    }

    @Override
    public Page<TransactionResponse> getLstTrans(Long transType, Pageable pageable) {
        Page<PaymentTransDetail> payment = paymentTransDetailRepository.findAllByTransTypeAndAccountPayment_AccountId(transType, AuditorUtils.getUserIdInToken(), pageable);
        return payment.map(paymentMapper::getTransMapper);
    }

    @Override
    public Page<PaymentOutCompilationResponse> getOutCompilation(Pageable pageable, String search) {
        Page<PaymentTransDetail> payment = paymentTransDetailRepository.findAllByTransTypeAndAccountPayment_FullNameContaining(1L, search,pageable);
        return payment.map(paymentMapper::getOutCompilation);
    }

    @Override
    public List<PaymentOutCompilationResponse> getOutAllCompilation() {
        List<PaymentTransDetail> payment = paymentTransDetailRepository.findAllByTransType(1L);
        return payment.stream().map(paymentMapper::getOutCompilation).collect(Collectors.toList());
    }

    @Override
    public Page<PaymentInCompilationResponse> getInCompilation(Pageable pageable, String search) {
        Page<PaymentTransDetail> payment = paymentTransDetailRepository.findAllByTransTypeAndAccountPayment_FullNameContaining(0L, search,pageable);
        return payment.map(paymentMapper::getInCompilation);
    }

    @Override
    public List<PaymentInCompilationResponse> getInAllCompilation() {
        List<PaymentTransDetail> payment = paymentTransDetailRepository.findAllByTransType(0L);
        return payment.stream().map(paymentMapper::getInCompilation).collect(Collectors.toList());
    }
}
