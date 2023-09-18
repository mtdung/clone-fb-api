package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.booking.CancelBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.CreatedBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingBeforeApprove;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingStatusRequest;
import vn.edu.fpt.horo.dto.response.booking.CreatedBookingResponse;
import vn.edu.fpt.horo.dto.response.booking.GetBookingDetailResponse;
import vn.edu.fpt.horo.dto.response.booking.NumberBookingResponse;

import java.util.List;
import java.util.Map;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public interface BookingService {
    GetBookingDetailResponse getBookingDetail(String bookingId);

    CreatedBookingResponse createdBooking(CreatedBookingRequest request);

    void updateBookingStatus(BookingStatus status, String bookingId);

    void cancelBooking(String bookingId, CancelBookingRequest request);

    Page<GetBookingDetailResponse> getBookingList(String accountId, BookingStatus status, Pageable pageable);
    Page<GetBookingDetailResponse> getBookingListAdvisor(String advisor, BookingStatus status, Pageable pageable);

    void approveBooking(String bookingId);

    NumberBookingResponse getNumberBooking(String advisorId);
    
    void cancelBookingIfOutTimeAccept();

    void hanldeBookingIfNotHaveResponse();

    void finishBookingWaiting();

    void handleAdvisorAbsent();

    Page<GetBookingDetailResponse> getBookingListMultiStatus(String accountId, List<String> statuses, Pageable pageable);

    Page<GetBookingDetailResponse> getBookingListAdvisorMultiStatus(String advisor, List<String> statuses, Pageable pageable);

    void updateBookingBeforeApprove(UpdateBookingBeforeApprove request, String bookingId);

    void notiBeforeBooking();
}
