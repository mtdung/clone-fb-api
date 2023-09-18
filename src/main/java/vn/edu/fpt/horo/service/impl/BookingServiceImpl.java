package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.InterWalletStatus;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.constant.SlotTimeEnum;
import vn.edu.fpt.horo.dto.request.booking.CancelBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.CreatedBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingBeforeApprove;
import vn.edu.fpt.horo.dto.response.booking.CreatedBookingResponse;
import vn.edu.fpt.horo.dto.response.booking.GetBookingDetailResponse;
import vn.edu.fpt.horo.dto.response.booking.NumberBookingResponse;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.BookingMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.BookingService;
import vn.edu.fpt.horo.service.feign.NotificationFeignClient;
import vn.edu.fpt.horo.service.feign.SendNotificationRequest;
import vn.edu.fpt.horo.service.feign.SendSmsRequest;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TopicRepository topicRepository;
    private final BookingMapper bookingMapper;
    private final PackageServiceRepository packageServiceRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;
    private final InterWalletRepository interWalletRepository;
    private final CoinRepository coinRepository;

    private final NotificationFeignClient notificationFeignClient;

    @Override
    public GetBookingDetailResponse getBookingDetail(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Booking Id not exist"));
        return bookingMapper.mapBookingDetail(booking);
    }


    @Override
    public Page<GetBookingDetailResponse> getBookingList(String accountId, BookingStatus status, Pageable pageable) {
        accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not exist"));
        Page<Booking> bookings = bookingRepository.findAllByCreatedBy_AccountIdAndStatus(accountId, status, pageable);
        return bookings.map(bookingMapper::mapBookingDetail);
    }

    @Override
    public CreatedBookingResponse createdBooking(CreatedBookingRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Topic Id not exist"));
        _PackageService packageService = packageServiceRepository.findById(request.getServicePackageId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Service Package Id not exist"));
        Advisor advisor = advisorRepository.findById(request.getAdvisorId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor Id not exist"));
        if (AuditorUtils.getUserIdInToken().equals(advisor.getAccount().getAccountId())) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Cannot book your self !");
        }
        Booking booking = Booking.builder()
                .bookingTime(request.getBookingTime())
                .slot(request.getSlot())
                .problem(request.getProblem())
                .topic(topic)
                .type(request.getBookingType())
                .coin(packageService.getPrice())
                .packageService(packageService)
                .advisor(advisor)
                .status(BookingStatus.WAITING_FOR_APPROVAL)
                .build();
        BigDecimal minusCoin = booking.getPackageService().getPrice();
        Account account = accountRepository.findAccountByAccountId(AuditorUtils.getUserIdInToken())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account Id not exist"));
        BigDecimal amountAccount = account.getCoin().getAmount();
        if (amountAccount.compareTo(minusCoin) < 0) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account coin not enough");
        } else {
             return saveBooking(account, advisor, request, booking, amountAccount, minusCoin);
        }
    }

    private synchronized CreatedBookingResponse saveBooking(Account account, Advisor advisor, CreatedBookingRequest request, Booking booking, BigDecimal amountAccount, BigDecimal minusCoin){
        List<BookingStatus> bookingStatuses = lstBookingStatusNotFail();
        Booking bookingInTime = bookingRepository.findFirstByAdvisor_AdvisorIdAndSlotAndBookingTimeAndStatusIn(advisor.getAdvisorId(), request.getSlot(), request.getBookingTime(), bookingStatuses);
        account.getCoin().setAmount(amountAccount.subtract(minusCoin));
        if(bookingInTime != null){
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Slot was not empty !");
        }else {
            bookingRepository.save(booking);
            coinRepository.save(account.getCoin());
            InterWallet wallet = InterWallet.builder()
                    .amount(minusCoin)
                    .fromAccount(account)
                    .toAccount(booking.getAdvisor().getAccount())
                    .transactionId(booking.getBookingId())
                    .status(InterWalletStatus.WAITING)
                    .build();
            interWalletRepository.save(wallet);
            sendNotification(account.getAccountId(), "CREATED", "Booking cua ban da duoc khoi tao !");
            notificationFeignClient.sendSms(SendSmsRequest
                    .builder()
                    .phoneNumber(booking.getAdvisor().getAccount().getProfile().getPhoneNumber())
                    .message("Đã có người book bạn kiểm tra ngay nhé !")
                    .build());
            return bookingMapper.mapCreateBookingResponse(booking);
        }
    }

    List<BookingStatus> lstBookingStatusNotFail(){
        List<BookingStatus> bookingStatuses = new ArrayList<>();
        bookingStatuses.add(BookingStatus.APPROVED);
        bookingStatuses.add(BookingStatus.WAITING_FOR_COMPLETED);
        bookingStatuses.add(BookingStatus.PROCESSING);
        bookingStatuses.add(BookingStatus.COMPLETED);
        return bookingStatuses;
    }

    @Override
    public void updateBookingStatus(BookingStatus status, String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Booking Id not exist"));
        if (status.equals(BookingStatus.COMPLETED)) {
            handleCompletedBooking(booking);
        } else {
            booking.setStatus(status);
            bookingRepository.save(booking);
        }

    }

    @Override
    public void cancelBooking(String bookingId, CancelBookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Booking Id not exist"));
        booking.setStatus(BookingStatus.valueOf(request.getStatus()));
        booking.setReason(request.getReason());
        bookingRepository.save(booking);
        if (booking.getStatus() == BookingStatus.REJECTED) {
            refundBooking(booking);
            sendNotification(booking.getCreatedBy().getAccountId(), "REJECTED", "Booking cua ban da bi tu choi boi advisor !");
        }
        sendNotification(booking.getCreatedBy().getAccountId(), "CANCELLED", "Booking cua ban da huy thanh cong !");
    }

    @Override
    public Page<GetBookingDetailResponse> getBookingListAdvisor(String advisor, BookingStatus status, Pageable pageable) {
        advisorRepository.findById(advisor)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor Id not exist"));
        Page<Booking> bookings = bookingRepository.findAllByAdvisor_AdvisorIdAndStatus(advisor, status, pageable);

        return bookings.map(bookingMapper::mapBookingDetail);
    }

    @Override
    public void approveBooking(String bookingId) {
        String userId = AuditorUtils.getUserIdInToken();
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Booking Id not exist"));
        if (!booking.getAdvisor().getAccount().getAccountId().equals(userId)) {
            throw new BusinessException(ResponseStatusEnum.FORBIDDEN, "You did not have permission to approve booking");
        }
        booking.setStatus(BookingStatus.APPROVED);
        bookingRepository.save(booking);
        sendNotification(booking.getCreatedBy().getAccountId(), "Approved", "Booking cua ban da duoc chap thuan");
    }

    @Override
    public NumberBookingResponse getNumberBooking(String advisor) {
        Advisor advisor1 = advisorRepository.findById(advisor)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor Id not exist"));
        Integer numberBookingPending = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.WAITING_FOR_APPROVAL, advisor);
        Integer numberBookingCancel = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.CANCELLED, advisor);
        Integer numberBookingSuccess = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.COMPLETED, advisor);
        Integer numberBookingWaitUserAccept = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.WAITING_FOR_COMPLETED, advisor);
        Integer numberBookingApproved = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.APPROVED, advisor);
        Integer numberBookingProcessing = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.PROCESSING, advisor);
        Integer numberBookingRejected = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.REJECTED, advisor);
        Integer numberBookingOutTimeApprove = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.TIMEOUT, advisor);
        Integer numberBookingNotJoined = bookingRepository.countAllByStatusAndAdvisor_AdvisorId(BookingStatus.ADVISOR_ABSENT, advisor);
        Integer numberRate = advisor1.getRates().size();
        return NumberBookingResponse.builder()
                .canceled(numberBookingCancel)
                .completed(numberBookingSuccess)
                .numberRate(numberRate)
                .waitingForApproval(numberBookingPending)
                .waitingForCompleted(numberBookingWaitUserAccept)
                .approved(numberBookingApproved)
                .processing(numberBookingProcessing)
                .rejected(numberBookingRejected)
                .timeout(numberBookingOutTimeApprove)
                .notJoined(numberBookingNotJoined)
                .build();
    }

    @Override
    public void cancelBookingIfOutTimeAccept() {
        try {
            LocalTime now = LocalTime.now();
            if (now.getMinute() == 45) {
                int nextHour = now.plusHours(1).getHour();


                int nextSlot = SlotTimeEnum.getByStartHour(nextHour).getSlot();
                if (nextSlot != 0) {
                    List<Booking> lstBookings =
                            bookingRepository.findAllBookingInDayBySlotAndByStatus(nextSlot, BookingStatus.WAITING_FOR_APPROVAL.toString());
                    for (Booking booking : lstBookings
                    ) {
                        refundBooking(booking);
                        booking.setStatus(BookingStatus.TIMEOUT);
                        bookingRepository.save(booking);
                        sendNotification(booking.getCreatedBy().getAccountId(), "TIMEOUT", "Booking cua ban da qua thoi gian cho approval !");
                        notificationFeignClient.sendSms(SendSmsRequest.builder()
                                        .phoneNumber(booking.getCreatedBy()
                                                .getProfile()
                                                .getPhoneNumber()
                                        )
                                        .message("Booking cua ban da qua thoi gian cho approval !")
                                .build());
                    }
                }
            }
        } catch (Exception e) {
            log.error("cancelBookingIfOutTimeAccept  {}", e.getMessage(), e);
        }
    }

    @Override
    public void hanldeBookingIfNotHaveResponse() {

        try {
            LocalTime now = LocalTime.now();
            if (now.getMinute() == 1) {
                int beforeHour = now.minusHours(1).getHour();
                int beforeSlot = SlotTimeEnum.getByEndHour(beforeHour).getSlot();
                if (beforeSlot != 0) {
                    List<Booking> lstBookings =
                            bookingRepository.findAllBookingInDayBySlotAndByStatus(beforeSlot, BookingStatus.PROCESSING.toString());
                    for (Booking booking : lstBookings
                    ) {
                        booking.setStatus(BookingStatus.WAITING_FOR_COMPLETED);
                        bookingRepository.save(booking);
                        sendNotification(booking.getCreatedBy().getAccountId(), "WAITING_FOR_COMPLETED", "Booking cua ban dang cho ban dong y hoan thanh !");
                    }
                }
            }
        } catch (Exception e) {
            log.error("cancelBookingIfOutTimeAccept  {}", e.getMessage(), e);
        }
    }

    @Override
    public void finishBookingWaiting() {
        List<Booking> lstBookings =
                bookingRepository.findAllBookingInDayByDayBeforeAndByStatus(2, BookingStatus.WAITING_FOR_COMPLETED.toString());
        for (Booking booking : lstBookings
        ) {
            handleCompletedBooking(booking);
        }
    }

    @Override
    public void handleAdvisorAbsent() {
        try {
            LocalTime now = LocalTime.now();
            if (now.getMinute() == 10) {
                int hourNow = now.getHour();
                int slotNow = SlotTimeEnum.getByStartHour(hourNow).getSlot();
                if (slotNow != 0) {
                    List<Booking> lstBookings =
                            bookingRepository.findAllBookingInDayBySlotAndByStatus(slotNow, BookingStatus.APPROVED.toString());
                    for (Booking booking : lstBookings
                    ) {
                        refundBooking(booking);
                        booking.setStatus(BookingStatus.ADVISOR_ABSENT);
                        bookingRepository.save(booking);
                        sendNotification(booking.getCreatedBy().getAccountId(), "ADVISOR_ABSENT", "Booking cua ban da bi huy do advisor khong tham gia, ban se duoc hoan tien !");
                    }
                }
            }
        } catch (Exception e) {
            log.error("handleAdvisorAbsent {}", e.getMessage(), e);
        }
    }

    @Override
    public Page<GetBookingDetailResponse> getBookingListMultiStatus(String accountId, List<String> statuses, Pageable pageable) {
        accountRepository.findAccountByAccountId(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account ID not exist"));
        List<BookingStatus> lstStatus = new ArrayList<>();
        for (String status : statuses
        ) {
            lstStatus.add(BookingStatus.valueOf(status));
        }
        Page<Booking> bookings = bookingRepository.findAllByCreatedBy_AccountIdAndStatusIn(accountId, lstStatus, pageable);
        return bookings.map(bookingMapper::mapBookingDetail);
    }

    @Override
    public Page<GetBookingDetailResponse> getBookingListAdvisorMultiStatus(String advisor, List<String> statuses, Pageable pageable) {
        advisorRepository.findById(advisor)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor Id not exist"));
        List<BookingStatus> lstStatus = new ArrayList<>();
        for (String status : statuses
        ) {
            lstStatus.add(BookingStatus.valueOf(status));
        }
        Page<Booking> bookings = bookingRepository.findAllByAdvisor_AdvisorIdAndStatusIn(advisor, lstStatus, pageable);
        return bookings.map(bookingMapper::mapBookingDetail);
    }

    @Override
    public void updateBookingBeforeApprove(UpdateBookingBeforeApprove request, String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Booking Id not exist"));
        BookingStatus status = booking.getStatus();
        if (status == BookingStatus.APPROVED) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "You cannot change this meeting schedule because advisor is already");
        }
        booking.setSlot(request.getSlot());
        booking.setBookingTime(request.getBookingTime());
        bookingRepository.save(booking);
    }

    @Override
    public void notiBeforeBooking() {
        try {
            LocalTime now = LocalTime.now();
            if (now.getMinute() == 55) {
                int hourNext = now.plusHours(1).getHour();
                int slotNext = SlotTimeEnum.getByStartHour(hourNext).getSlot();
                if (slotNext != 0) {
                    List<Booking> lstBookings =
                            bookingRepository.findAllBookingInDayBySlotAndByStatus(slotNext, BookingStatus.APPROVED.toString());
                    for (Booking booking : lstBookings
                    ) {
                        notificationFeignClient.sendSms(SendSmsRequest
                                .builder()
                                .phoneNumber(booking.getAdvisor().getAccount().getProfile().getPhoneNumber())
                                .message("Còn 5 phút nữa là bắt đầu booking của bạn !")
                                .build());
                    }
                }
            }
        } catch (Exception e) {
            log.error("handleAdvisorAbsent {}", e.getMessage(), e);
        }
    }

    private void handleCompletedBooking(Booking booking) {
        booking.setStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);
        InterWallet wallet = interWalletRepository.findByTransactionIdAndStatus(booking.getBookingId(), InterWalletStatus.WAITING)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Transaction was handled"));
        Account advisor = booking.getAdvisor().getAccount();
        advisor.getCoin().setAmount(advisor.getCoin().getAmount().add(wallet.getAmount()));
        accountRepository.save(advisor);
        sendNotification(advisor.getAccountId(), "COMPLETED", "Booking " + booking.getBookingId() + " đã được thanh toán thành công !");
        wallet.setStatus(InterWalletStatus.SUCCESS);
        interWalletRepository.save(wallet);
    }

    private void refundBooking(Booking booking) {
        InterWallet wallet = interWalletRepository.findByTransactionIdAndStatus(booking.getBookingId(), InterWalletStatus.WAITING)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Transaction was handled"));
        BigDecimal priceCoin = wallet.getAmount();
        Account account = accountRepository.findAccountByAccountId(booking.getCreatedBy().getAccountId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account Id not exist"));
        BigDecimal amountAccount = account.getCoin().getAmount();
        account.getCoin().setAmount(amountAccount.add(priceCoin));
        coinRepository.save(account.getCoin());
        sendNotification(account.getAccountId(), "REFUND", "Ban da duoc hoan tien Booking " + booking.getBookingId());
        wallet.setStatus(InterWalletStatus.FAILURE);
    }

    private void sendNotification(String accountId, String title, String content) {
        notificationFeignClient.sendNotification(SendNotificationRequest.builder()
                .userId(accountId)
                .title(title)
                .body(content)
                .build());
    }
}
