package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.booking.CancelBookingRequest;
import vn.edu.fpt.fb.dto.request.booking.CreatedBookingRequest;
import vn.edu.fpt.fb.dto.request.booking.UpdateBookingBeforeApprove;
import vn.edu.fpt.fb.dto.response.booking.CreatedBookingResponse;
import vn.edu.fpt.fb.dto.response.booking.GetBookingDetailResponse;
import vn.edu.fpt.fb.dto.response.booking.NumberBookingResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingControllerImpl implements BookingController {
    private final ResponseFactory responseFactory;
    private final BookingService bookingService;

    @Override
    public ResponseEntity<GeneralResponse<CreatedBookingResponse>> createBooking(CreatedBookingRequest request) {
        return responseFactory.response(bookingService.createdBooking(request));
    }


    @Override
    public ResponseEntity<GeneralResponse<Object>> updateBooking(BookingStatus status, String bookingId) {
        bookingService.updateBookingStatus(status, bookingId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateBookingBeforeApprove(UpdateBookingBeforeApprove request, String bookingId) {
        bookingService.updateBookingBeforeApprove(request, bookingId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<GetBookingDetailResponse>> getBookingDetails(String bookingId) {
        return responseFactory.response(bookingService.getBookingDetail(bookingId));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingList(String accountId, BookingStatus status, Pageable pageable) {
        return responseFactory.response(bookingService.getBookingList(accountId, status, pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListMultiStatus(String accountId, List<String> statuses, Pageable pageable) {
        return responseFactory.response(bookingService.getBookingListMultiStatus(accountId, statuses , pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<NumberBookingResponse>> getNumberBooking(String advisorId) {
        return responseFactory.response(bookingService.getNumberBooking(advisorId));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListAdvisor(String advisor, BookingStatus status, Pageable pageable) {
        return responseFactory.response(bookingService.getBookingListAdvisor(advisor, status, pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListAdvisorMultiStatus(String advisor, List<String> statuses, Pageable pageable) {
        return responseFactory.response(bookingService.getBookingListAdvisorMultiStatus(advisor, statuses, pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> cancelBooking(String bookingId, CancelBookingRequest request) {
        bookingService.cancelBooking(bookingId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> approveBookingRequest(String bookingId) {
        bookingService.approveBooking(bookingId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }
}
