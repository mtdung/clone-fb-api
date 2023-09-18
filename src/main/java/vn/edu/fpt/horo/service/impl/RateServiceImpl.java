package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.rate.CreateRateRequest;
import vn.edu.fpt.horo.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.horo.dto.response.rate.NumberRateResponse;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.repository.AdvisorRepository;
import vn.edu.fpt.horo.repository.RateRepository;
import vn.edu.fpt.horo.service.RateService;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.util.List;

/**
 * vn.edu.fpt.horo.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 29/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final AdvisorRepository advisorRepository;

    private final AccountRepository accountRepository;

    private final RateRepository rateRepository;


    @Override
    public CreateRateResponse createRateAdvisor(CreateRateRequest request) {
        Advisor advisor = advisorRepository.findById(request.getAdvisorId())
                .orElseThrow();
        Account account = accountRepository.findAccountByAccountId(AuditorUtils.getUserIdInToken())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not exist"));
        Rate rate = Rate.builder()
                .point(request.getPoint())
                .content(request.getReview())
                .rateBy(account)
                .build();
        rate = rateRepository.save(rate);
        Float totalRates = advisor.getTotalRate();
        List<Rate> rates = advisor.getRates();
        if (rates.isEmpty()) {
            rates.add(rate);
            totalRates = request.getPoint();
        } else {
            totalRates = (totalRates * rates.size() + request.getPoint()) / (rates.size() + 1);
            rates.add(rate);
        }
        advisor.setRates(rates);
        advisor.setTotalRate(totalRates);
        advisorRepository.save(advisor);
        return CreateRateResponse.builder()
                .rateId(rate.getRateId())
                .build();
    }



}
