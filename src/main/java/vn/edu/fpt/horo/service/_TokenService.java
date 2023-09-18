package vn.edu.fpt.horo.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.fpt.horo.dto.request.account.LoginRequest;
import vn.edu.fpt.horo.entity.Account;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.service
 **/

public interface _TokenService {

    Optional<Authentication> getAuthenticationFromToken(String token);

    String generateToken(Account account, UserDetails userDetails);

    LocalDateTime getExpiredTimeFromToken(String token);

    String generateRefreshToken(LoginRequest request);

    LoginRequest getLoginRequestFromToken(String token);

}
