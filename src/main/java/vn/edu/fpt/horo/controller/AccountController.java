package vn.edu.fpt.horo.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.config.security.annotation.IsAdmin;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.CreateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.account.*;
import vn.edu.fpt.horo.dto.response.account.*;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.CreateAdvisorTicketResponse;
import vn.edu.fpt.horo.dto.response.follow.GetCountFollowPost;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RequestMapping("${app.application-context}/public/api/v1/accounts")
public interface AccountController {

    @PostMapping("/{account-id}/create-advisor")
    ResponseEntity<GeneralResponse<CreateAdvisorTicketResponse>> requestCreateAdvisor(@PathVariable("account-id") String accountId, @RequestBody CreateAdvisorTicketRequest request);

    @GetMapping("/{account-id}/advisor")
    ResponseEntity<GeneralResponse<GetAdvisorResponse>> getAdvisorDetail(@PathVariable("account-id") String accountId);

    @PostMapping("/{account-id}/advisor/check")
    ResponseEntity<GeneralResponse<Boolean>> checkIsAdvisor(@PathVariable("account-id") String accountId);

    @PostMapping
    ResponseEntity<GeneralResponse<CreateAccountResponse>> createAccount(@RequestBody CreateAccountRequest request);

    @GetMapping("/{account-id}")
    ResponseEntity<GeneralResponse<AccountResponse>> getAccountDetail(@PathVariable(name = "account-id") String accountId);

    @PostMapping("/account/login")
    ResponseEntity<GeneralResponse<LoginResponse>> login(@RequestBody LoginRequest request);

    @PostMapping("/account/login/gg")
    ResponseEntity<GeneralResponse<LoginResponse>> loginWithGoogle(@RequestBody LoginGgRequest email);

    @PostMapping("/token/refresh")
    ResponseEntity<GeneralResponse<LoginResponse>> refreshToken(@RequestBody RefreshTokenRequest request);

    @PutMapping("/{account-id}/change-online-status")
    ResponseEntity<GeneralResponse<Object>> changeOnlineStatus(
            @PathVariable("account-id") String accountId,
            @RequestBody Boolean isOn);

    @PutMapping("/{id}/password")
    ResponseEntity<GeneralResponse<Object>> changePassword(
            @PathVariable("id") String id,
            @RequestBody ChangePasswordRequest request);

    @PutMapping("/{id}/email")
    ResponseEntity<GeneralResponse<Object>> changeEmail(
            @PathVariable String id,
            @RequestBody ChangeEmailRequest request);


    @PostMapping("/password/reset")
    ResponseEntity<GeneralResponse<Object>> resetPassword(@RequestBody ResetPasswordRequest request);

    @DeleteMapping("/{id}")
    @IsAdmin
    ResponseEntity<GeneralResponse<Object>> deleteAccountById(@PathVariable("id") String id);

    @PostMapping("/{id}/role")
    @IsAdmin
    ResponseEntity<GeneralResponse<Object>> addRoleToAccount(
            @PathVariable("id") String id,
            @RequestBody AddRoleToAccountRequest request);

    @DeleteMapping("/{id}/roles/{role-id}")
    @IsAdmin
    ResponseEntity<GeneralResponse<Object>> removeRoleFromAccount(
            @PathVariable("id") String id,
            @PathVariable("role-id") String roleId
    );

    @PostMapping("/{advisor-id}/following")
    ResponseEntity<GeneralResponse<Object>> folowingAdvisor(@PathVariable(name = "advisor-id") String advisorId);

    @GetMapping("/{account-id}/following")
    ResponseEntity<GeneralResponse<Page<GetAdvisorResponse>>> getAccountFollowing(
            @PathVariable(name = "account-id") String accountId,
            @ParameterObject Pageable pageable);

    @GetMapping("/{advisor-id}/follower")
    ResponseEntity<GeneralResponse<Page<AccountResponse>>> getAdvisorFollower(
            @PathVariable(name = "advisor-id") String advisorId,
            @ParameterObject Pageable pageable);

    @GetMapping("/{account-id}/count-follow-post")
    ResponseEntity<GeneralResponse<GetCountFollowPost>> getCountFollowPost(@PathVariable("account-id") String accountId);
}
