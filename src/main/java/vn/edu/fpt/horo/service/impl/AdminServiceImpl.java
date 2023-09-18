package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.constant.SlotTimeEnum;
import vn.edu.fpt.horo.dto.admin.*;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.entity.custom.PaymentTransAmountEntity;
import vn.edu.fpt.horo.entity.custom.TopicNumberUse;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.AccountMapper;
import vn.edu.fpt.horo.mapper.BookingMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.AdminService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdvisorTicketRepository advisorTicketRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PackageServiceRepository packageServiceRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PaymentTransDetailRepository paymentTransDetailRepository;

    @Autowired
    AdvisorRepository advisorRepository;

    @Autowired
    S3BucketStorageServiceImpl s3BucketStorageService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CoinRepository coinRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    AccountMapper accountMapper;


    @Override
    public DashBoardResponse getDashBoard() {
        PaymentTransAmountEntity paymentTrans = paymentTransDetailRepository.getTotalTransByTransType(0L);
        PaymentTransAmountEntity paymentWithdraw = paymentTransDetailRepository.getTotalTransByTransType(1L);
        return DashBoardResponse.builder()
                .totalMoneyTrans(paymentTrans.getTransAmount() == null ? BigDecimal.ZERO : paymentTrans.getTransAmount())
                .totalWithdraw(paymentWithdraw.getTransAmount() == null ? BigDecimal.ZERO : paymentWithdraw.getTransAmount())
                .totalItemTrans(paymentTrans.getTransAmount() == null ? BigDecimal.ZERO : paymentTrans.getCoinTransAmount())
                .totalItemWithDraw(paymentWithdraw.getCoinTransAmount() == null ? BigDecimal.ZERO : paymentWithdraw.getCoinTransAmount())
                .adminCollect(paymentTrans.getAdminCollectAmount() == null ? BigDecimal.ZERO : paymentTrans.getAdminCollectAmount())
                .countAdvisor(Long.valueOf(advisorRepository.findAll().size()))
                .countOfUser(Long.valueOf(accountRepository.findAll().size()))
                .countAdvisorRequest(advisorTicketRepository.count())
                .countCancelRequest(advisorTicketRepository.countAllByAdvisorIsNull())
                .countPost(Long.valueOf(postRepository.findAll().size()))
                .pendingBooking(bookingRepository.countAllByStatus(BookingStatus.WAITING_FOR_APPROVAL))
                .cancelBooking(bookingRepository.countAllByStatus(BookingStatus.CANCELLED))
                .successBooking(bookingRepository.countAllByStatus(BookingStatus.COMPLETED))
                .build();
    }

    @Override
    public RequestAdvisorResponse getDetailReqAdvisor(String ticketId) {
        AdvisorTicket ticket = advisorTicketRepository.findFirstByTicketId(ticketId);
        String frontImg = s3BucketStorageService.sharingUsingPresignedURL(ticket.getFontIdImage().getFileKey());
        String backImg = s3BucketStorageService.sharingUsingPresignedURL(ticket.getBackIdImage().getFileKey());
        String portraitImg = s3BucketStorageService.sharingUsingPresignedURL(ticket.getPortraitImage().getFileKey());
        return RequestAdvisorResponse.builder()
                .ticketId(ticket.getTicketId())
                .advisorId(ticket.getAdvisor().getAdvisorId())
                .accountName(ticket.getAdvisor().getAccount().getFullName())
                .cardId(ticket.getCardId())
                .issuedPlace(ticket.getIssuedPlace())
                .issuedDate(ticket.getIssuedDate())
                .status(ticket.getTicketStatus())
                .type(ticket.getType().toString())
                .issuedBy(ticket.getIssuedBy())
                .gender(ticket.getGender().toString())
                .fontIdImageLink(frontImg)
                .backIdImageLink(backImg)
                .portraitImageLink(portraitImg)
                .summary(ticket.getRequest())
                .description(ticket.getDescription())
                .build();
    }

    @Override
    public Page<RequestListAdvisorResponse> getLstReqAdvisor(Pageable pageable, String search) {
        List<AdvisorTicket> advisorTickets = advisorTicketRepository.findAllByAdvisorIsNotNullAndCreatedBy_FullNameContaining(search);
        List<RequestListAdvisorResponse> lst = new ArrayList<RequestListAdvisorResponse>();
        for (AdvisorTicket advisorTicket : advisorTickets) {
            lst.add(RequestListAdvisorResponse.builder()
                    .ticketId(advisorTicket.getTicketId())
                    .advisorId(advisorTicket.getAdvisor().getAdvisorId())
                    .accountName(advisorTicket.getAdvisor().getAccount().getFullName())
                    .createDate(advisorTicket.getCreatedDate())
                    .type(advisorTicket.getType().toString())
                    .status(advisorTicket.getTicketStatus())
                    .build());
        }
        int indexFrom = pageable.getPageSize() * pageable.getPageNumber();
        int indexTo = indexFrom + pageable.getPageSize();
        int size = lst.size();
        Page<RequestListAdvisorResponse> lstPage;
        if (size < indexFrom) {
            lstPage = new PageImpl<>(Collections.emptyList(), pageable, size);
        } else if (size < indexTo) {
            lstPage = new PageImpl<>(lst.subList(indexFrom, size), pageable, size);
        } else {
            lstPage = new PageImpl<>(lst.subList(indexFrom, indexTo), pageable, size);
        }
        return lstPage;
    }

    @Override
    public List<GetTopicUseResponse> getTopicUse() {
        List<TopicNumberUse> topicUses = packageServiceRepository.getTopicNumberUse();
        List<GetTopicUseResponse> lst = new ArrayList<>();
        for (TopicNumberUse topicUse : topicUses) {
            lst.add(GetTopicUseResponse.builder()
                    .topicId(topicUse.getTopicId())
                    .topicName(topicUse.getTopicName())
                    .numberUseTopic(topicUse.getNumberUseTopic())
                    .isActive(topicUse.getIsActive())
                    .build());
        }
        return lst;
    }

    @Override
    public GetDetailAdvisorResponse getDetailAdvisor(String accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));

        Advisor advisor = advisorRepository.findFirstByAccount_AccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor not found"));

        String frontImg = s3BucketStorageService.sharingUsingPresignedURL(advisor.getFontIdImage().getFileKey());
        String backImg = s3BucketStorageService.sharingUsingPresignedURL(advisor.getBackIdImage().getFileKey());
        String portraitImg = s3BucketStorageService.sharingUsingPresignedURL(advisor.getPortraitImage().getFileKey());
        return GetDetailAdvisorResponse.builder()
                .advisorId(advisor.getAdvisorId())
                .accountName(account.getFullName())
                .email(account.getEmail())
                .phoneNumber(account.getProfile().getPhoneNumber())
                .cardId(advisor.getCardId())
                .issuedPlace(advisor.getIssuedPlace())
                .issuedDate(advisor.getIssuedDate())
                .issuedBy(advisor.getIssuedBy())
                .gender(advisor.getGender().toString())
                .fontIdImageLink(frontImg)
                .backIdImageLink(backImg)
                .portraitImageLink(portraitImg)
                .build();
    }

    @Override
    public void changeWithdrawStatus(String paymentTransId, ChangeWithDrawStatusRequest request) {
        PaymentTransDetail payment = paymentTransDetailRepository.findFirstByIdAndErrorCodeIsNull(paymentTransId);
        payment.setEndDate(LocalDateTime.now());
        payment.setStatus("00".equals(request.getErrorCode()));
        payment.setErrorCode(request.getErrorCode());
        payment.setErrorMessage(request.getErrorMessage());
        paymentTransDetailRepository.save(payment);
        if (!"00".equals(request.getErrorCode())) {
            Account account = accountRepository.findById(payment.getAccountPayment().getAccountId())
                    .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
            Coin coin = account.getCoin();
            coin.setAmount(coin.getAmount().add(payment.getCoinTransAmount()));
            coinRepository.save(coin);
        }
    }

    @Override
    public Page<AllBookingInfoResponse> getAllBookingInfo(Pageable pageable, String search) {
        Page<Booking> allBookingInfo = bookingRepository.findAllByBookingIdContainingOrAdvisor_Account_FullNameContaining(search, search, pageable);
        Page<AllBookingInfoResponse> responses = allBookingInfo.map(bookingMapper::mapAllBookingsInfoResponse);
        for (AllBookingInfoResponse response : responses) {
            SlotTimeEnum slt = SlotTimeEnum.getBySlot(response.getSlot());
            LocalDateTime bookingTime = response.getBookingTime();
            response.setStartTime(bookingTime.withHour(slt.getStartTime()).withMinute(0));
            response.setEndTime(bookingTime.withHour(slt.getEndTime()).withMinute(0));
        }
        return responses;
    }

    @Override
    public Page<AllUserInfoResponse> getAllUser(Pageable pageable, String search) {
        Page<Account> allAccounts = accountRepository.findAllByAccountIdContainingOrFullNameContainingOrProfile_PhoneNumberContaining(search, search, search, pageable);
        Page<AllUserInfoResponse> allUserInfoResponse = allAccounts.map(accountMapper::mapGetUserInfo);
        return allUserInfoResponse;
    }

    @Override
    public UserInfoDetailsResponse getUserInfoDetails(String accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account not found"));
        UserInfoDetailsResponse details = accountMapper.mapGetUserInfoDetails(account);
        int success = bookingRepository.countAllByCreatedBy_AccountIdAndStatus(accountId, BookingStatus.COMPLETED);
        int fail = bookingRepository.countAllByCreatedBy_AccountIdAndStatusIn(accountId, Arrays.asList(BookingStatus.CANCELLED, BookingStatus.REJECTED, BookingStatus.ADVISOR_ABSENT));
        details.setSuccessBooking(success);
        details.setFailureBooking(fail);
        return details;
    }
}
