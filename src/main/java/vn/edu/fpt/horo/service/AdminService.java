package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.dto.admin.*;

import java.util.List;

public interface AdminService {

    DashBoardResponse getDashBoard();

    RequestAdvisorResponse getDetailReqAdvisor(String ticketId);

    Page<RequestListAdvisorResponse> getLstReqAdvisor(Pageable pageable, String search);

    List<GetTopicUseResponse> getTopicUse();

    GetDetailAdvisorResponse getDetailAdvisor(String accountId);

    void changeWithdrawStatus(String paymentTransId, ChangeWithDrawStatusRequest request);

    Page<AllBookingInfoResponse> getAllBookingInfo(Pageable pageable, String search);

    Page<AllUserInfoResponse> getAllUser(Pageable pageable, String search);

    UserInfoDetailsResponse getUserInfoDetails(String accountId);
}
