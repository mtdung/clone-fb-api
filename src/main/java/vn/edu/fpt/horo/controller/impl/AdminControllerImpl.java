package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.config.security.annotation.IsAdmin;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.AdminController;
import vn.edu.fpt.horo.dto.admin.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.topic.CreateTopicResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.AdminService;
import vn.edu.fpt.horo.service.AdvisorTicketService;
import vn.edu.fpt.horo.service.PaymentService;
import vn.edu.fpt.horo.service.TopicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminControllerImpl implements AdminController {
    private final ResponseFactory responseFactory;
    private final AdvisorTicketService advisorTicketService;
    private final TopicService topicService;
    private final AdminService adminService;
    private final PaymentService paymentService;

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Object>> approveTicket(String ticketId, ApproveTicketRequest request) {
        advisorTicketService.approveTicket(ticketId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<RequestAdvisorResponse>> getDetailReqAdvisor(String ticketId) {
        return responseFactory.response(adminService.getDetailReqAdvisor(ticketId));
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Page<RequestListAdvisorResponse>>> getListReqAdvisor(Pageable pageable, String search) {
        return responseFactory.response(adminService.getLstReqAdvisor(pageable, search));
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<DashBoardResponse>> getDashBoardDetail() {
        return responseFactory.response(adminService.getDashBoard());
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<GetDetailAdvisorResponse>> getDetailAdvisor(String accountId) {
        return responseFactory.response(adminService.getDetailAdvisor(accountId));
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Page<GetListWithDrawResponse>>> getRequestWithdraw(Pageable pageable, String search) {
        return responseFactory.response(paymentService.getListWithDraw(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<PaymentOutCompilationResponse>>> getOutCompilation(Pageable pageable, String search) {
        return responseFactory.response(paymentService.getOutCompilation(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<List<PaymentOutCompilationResponse>>> getAllOutCompilation() {
        return responseFactory.response(paymentService.getOutAllCompilation());
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<PaymentInCompilationResponse>>> getInCompilation(Pageable pageable, String search) {
        return responseFactory.response(paymentService.getInCompilation(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<List<PaymentInCompilationResponse>>> getAllInCompilation() {
        return responseFactory.response(paymentService.getInAllCompilation());
    }


    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Object>> changeWithDrawStatus(String paymentTransId, ChangeWithDrawStatusRequest request) {
        adminService.changeWithdrawStatus(paymentTransId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<List<GetTopicUseResponse>>> getTopicUse() {
        return responseFactory.response(adminService.getTopicUse());
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<CreateTopicResponse>> createTopic(CreateTopicRequest request) {
        return responseFactory.response(topicService.createTopic(request));
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Object>> updateTopicActive(String topicId) {
        topicService.offTopic(topicId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<AllBookingInfoResponse>>> getAllBooking(Pageable pageable, String search) {
        return responseFactory.response(adminService.getAllBookingInfo(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<AllUserInfoResponse>>> getAllUser(Pageable pageable, String search) {
        return responseFactory.response(adminService.getAllUser(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<UserInfoDetailsResponse>> getUserDetails(String accountId) {
        return responseFactory.response(adminService.getUserInfoDetails(accountId));
    }
}
