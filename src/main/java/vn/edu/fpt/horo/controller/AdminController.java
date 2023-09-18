package vn.edu.fpt.horo.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.admin.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.topic.CreateTopicResponse;

import java.util.List;

@RequestMapping("${app.application-context}/public/api/v1/admin")

public interface AdminController {
    @PostMapping("/tickets/{ticket-id}")
    ResponseEntity<GeneralResponse<Object>> approveTicket(@PathVariable(name = "ticket-id") String ticketId, @RequestBody ApproveTicketRequest request);

    @GetMapping("/tickets/{ticket-id}")
    ResponseEntity<GeneralResponse<RequestAdvisorResponse>> getDetailReqAdvisor(@PathVariable(name = "ticket-id") String ticketId);

    @GetMapping("/tickets/list")
    ResponseEntity<GeneralResponse<Page<RequestListAdvisorResponse>>> getListReqAdvisor(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/get/dash-board")
    ResponseEntity<GeneralResponse<DashBoardResponse>> getDashBoardDetail();

    @GetMapping("/advisor/{account-id}")
    ResponseEntity<GeneralResponse<GetDetailAdvisorResponse>> getDetailAdvisor(@PathVariable(name = "account-id") String accountId);

    @GetMapping("/get/get-request-withdraw")
    ResponseEntity<GeneralResponse<Page<GetListWithDrawResponse>>> getRequestWithdraw(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/get/payment-out-compilation")
    ResponseEntity<GeneralResponse<Page<PaymentOutCompilationResponse>>> getOutCompilation(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/get/all/payment-out-compilation")
    ResponseEntity<GeneralResponse<List<PaymentOutCompilationResponse>>> getAllOutCompilation();

    @GetMapping("/get/payment-in-compilation")
    ResponseEntity<GeneralResponse<Page<PaymentInCompilationResponse>>> getInCompilation(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/get/all/payment-in-compilation")
    ResponseEntity<GeneralResponse<List<PaymentInCompilationResponse>>> getAllInCompilation();


    @PostMapping("/{payment-trans-id}/handle-withdraw")
    ResponseEntity<GeneralResponse<Object>> changeWithDrawStatus(@PathVariable(name = "payment-trans-id") String paymentTransId,@RequestBody ChangeWithDrawStatusRequest request);

    @GetMapping("/topic/number-use-topic")
    ResponseEntity<GeneralResponse<List<GetTopicUseResponse>>> getTopicUse();

    @PostMapping("topic/create-topic")
    ResponseEntity<GeneralResponse<CreateTopicResponse>> createTopic(@RequestBody CreateTopicRequest request);

    @PutMapping("topic")
    ResponseEntity<GeneralResponse<Object>> updateTopicActive(@RequestBody String topicId);

    @GetMapping("/booking")
    ResponseEntity<GeneralResponse<Page<AllBookingInfoResponse>>> getAllBooking(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/user")
    ResponseEntity<GeneralResponse<Page<AllUserInfoResponse>>> getAllUser(@ParameterObject Pageable pageable, @RequestParam("search") String search);

    @GetMapping("/user/details")
    ResponseEntity<GeneralResponse<UserInfoDetailsResponse>> getUserDetails(@RequestParam("account-id") String accountId );
}
