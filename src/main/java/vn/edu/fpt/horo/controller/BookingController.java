package vn.edu.fpt.horo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.booking.CancelBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.CreatedBookingRequest;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingBeforeApprove;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingStatusRequest;
import vn.edu.fpt.horo.dto.response.booking.CreatedBookingResponse;
import vn.edu.fpt.horo.dto.response.booking.GetBookingDetailResponse;
import vn.edu.fpt.horo.dto.response.booking.NumberBookingResponse;

import java.util.List;
import java.util.Map;

@RequestMapping("${app.application-context}/public/api/v1/bookings")
public interface BookingController {
    @PostMapping
    ResponseEntity<GeneralResponse<CreatedBookingResponse>> createBooking(@RequestBody CreatedBookingRequest request);

    // update trang thai status cua booking
    @PutMapping("/{booking-id}")
    ResponseEntity<GeneralResponse<Object>> updateBooking(@RequestParam(name = "status") BookingStatus status, @PathVariable("booking-id") String bookingId);

    @PutMapping("/{booking-id}/calendar")
    ResponseEntity<GeneralResponse<Object>> updateBookingBeforeApprove(@RequestBody UpdateBookingBeforeApprove request, @PathVariable("booking-id") String bookingId);

    @GetMapping("/{booking-id}")
    ResponseEntity<GeneralResponse<GetBookingDetailResponse>> getBookingDetails(@PathVariable(name = "booking-id") String bookingId);

    @GetMapping("/accounts/{account-id}/")
    ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingList(
            @PathVariable(name = "account-id") String accountId,
            @RequestParam(name = "status") BookingStatus status,
            @ParameterObject Pageable pageable
    );

    @GetMapping("/accounts/multi-status/{account-id}/")
    ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListMultiStatus(
            @PathVariable(name = "account-id") String accountId,
            @RequestParam("status") List<String> status,
            @ParameterObject Pageable pageable
    );

    @GetMapping("/count-booking/{advisor-id}")
    ResponseEntity<GeneralResponse<NumberBookingResponse>> getNumberBooking(@PathVariable(name = "advisor-id") String advisorId);

    @GetMapping("/advisors/{advisor-id}")
    ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListAdvisor(
            @PathVariable(name = "advisor-id") String advisor,
            @RequestParam(name = "status") BookingStatus status,
            @ParameterObject Pageable pageable
    );

    @GetMapping("/advisors/multi-status/{advisor-id}/")
    ResponseEntity<GeneralResponse<Page<GetBookingDetailResponse>>> getBookingListAdvisorMultiStatus(
            @PathVariable(name = "advisor-id") String advisor,
            @RequestParam("status") List<String> status,
            @ParameterObject Pageable pageable
    );

    @PutMapping("/{booking-id}/approval")
    @Operation(description = "Phê duyệt booking")
    ResponseEntity<GeneralResponse<Object>> approveBookingRequest(@PathVariable(name = "booking-id") String bookingId);

    @PutMapping("/{booking-id}/cancel")
    @Operation(description = "Từ chối booking")
    ResponseEntity<GeneralResponse<Object>> cancelBooking(@PathVariable("booking-id") String bookingId, @RequestBody CancelBookingRequest request);

}
