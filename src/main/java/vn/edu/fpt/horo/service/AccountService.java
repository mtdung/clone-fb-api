package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.account.*;
import vn.edu.fpt.horo.dto.response.account.*;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.follow.GetCountFollowPost;

/**
 * vn.edu.fpt.accounts.service
 **/

public interface AccountService {

    void init();

    UserDetails getUserByUsername(String username);

    void changeEmail(String id, ChangeEmailRequest request);

    void changePassword(String id, ChangePasswordRequest request);

    CreateAccountResponse createAccount(CreateAccountRequest request);

    LoginResponse login(LoginRequest request);

    LoginResponse loginWithGoogle(String email);

    LoginResponse refreshToken(RefreshTokenRequest request);

    void resetPassword(ResetPasswordRequest request);

    void addRoleToAccount(String id, AddRoleToAccountRequest request);

    void deleteAccountById(String id);

    void removeRoleFromAccount(String id, String roleId);

    Boolean changeOnlineStatus(String accountId, Boolean isOn);

    AccountResponse getAccountDetail(String accountId);

    void folowingAdvisor(String advisorId);

    Page<GetAdvisorResponse> getAccountFollowings(String accountId, Pageable pageable);

    GetCountFollowPost getCountFollowPost(String accountId);
}

