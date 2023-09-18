package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.config.security.annotation.IsAdmin;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.AccountController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.CreateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.account.*;
import vn.edu.fpt.horo.dto.response.account.*;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.CreateAdvisorTicketResponse;
import vn.edu.fpt.horo.dto.response.follow.GetCountFollowPost;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.AccountService;
import vn.edu.fpt.horo.service.AdvisorService;
import vn.edu.fpt.horo.service.AdvisorTicketService;


/**
 * vn.edu.fpt.accounts.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;
    private final AdvisorService advisorService;
    private final AdvisorTicketService advisorTicketService;
    private final ResponseFactory responseFactory;


    @Override
    public ResponseEntity<GeneralResponse<CreateAdvisorTicketResponse>> requestCreateAdvisor(String accountId, CreateAdvisorTicketRequest request) {
        return responseFactory.response(advisorTicketService.requestCreateAdvisorTicket(accountId, request));
    }
    @Override
    public ResponseEntity<GeneralResponse<GetAdvisorResponse>> getAdvisorDetail(String accountId) {
        return responseFactory.response(advisorService.getAdvisorDetail(accountId));
    }

    @Override
    public ResponseEntity<GeneralResponse<Boolean>> checkIsAdvisor(String accountId) {
        return responseFactory.response(advisorService.checkIsAdvisor(accountId));
    }

    @Override
    public ResponseEntity<GeneralResponse<CreateAccountResponse>> createAccount(CreateAccountRequest request) {
        return responseFactory.response(accountService.createAccount(request), ResponseStatusEnum.CREATED);
    }

    @Override
    public ResponseEntity<GeneralResponse<AccountResponse>> getAccountDetail(String accountId) {
        return responseFactory.response(accountService.getAccountDetail(accountId));
    }


    @Override
    public ResponseEntity<GeneralResponse<LoginResponse>> login(LoginRequest request) {
        return responseFactory.response(accountService.login(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<LoginResponse>> loginWithGoogle(LoginGgRequest email) {
        return responseFactory.response(accountService.loginWithGoogle(email.getEmail()));
    }

    @Override
    public ResponseEntity<GeneralResponse<LoginResponse>> refreshToken(RefreshTokenRequest request) {
        return responseFactory.response(accountService.refreshToken(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> changeOnlineStatus(String accountId, Boolean isOn) {
        return responseFactory.response(accountService.changeOnlineStatus(accountId, isOn));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> changePassword(String id, ChangePasswordRequest request) {
        accountService.changePassword(id, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> changeEmail(String id, ChangeEmailRequest request) {
        accountService.changeEmail(id, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> resetPassword(ResetPasswordRequest request) {
        accountService.resetPassword(request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    @IsAdmin
    public ResponseEntity<GeneralResponse<Object>> deleteAccountById(String id) {
        accountService.deleteAccountById(id);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> addRoleToAccount(String id, AddRoleToAccountRequest request) {
        accountService.addRoleToAccount(id, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> removeRoleFromAccount(String id, String roleId) {
        accountService.removeRoleFromAccount(id, roleId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> folowingAdvisor(String advisorId) {
        accountService.folowingAdvisor(advisorId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetAdvisorResponse>>> getAccountFollowing(String accountId, Pageable pageable) {
        return responseFactory.response(accountService.getAccountFollowings(accountId, pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<AccountResponse>>> getAdvisorFollower(String advisorId, Pageable pageable) {
        return responseFactory.response(advisorService.getAccountFollower(advisorId, pageable));
    }

    @Override
    public ResponseEntity<GeneralResponse<GetCountFollowPost>> getCountFollowPost(String accountId) {
        return responseFactory.response(accountService.getCountFollowPost(accountId));
    }


}
