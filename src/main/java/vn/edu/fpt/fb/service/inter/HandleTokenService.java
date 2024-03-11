package vn.edu.fpt.fb.service.inter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.fpt.fb.dto.LoginRequest;
import vn.edu.fpt.fb.entity.SysUser;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HandleTokenService {

    Optional<Authentication> getAuthenticationFromToken(String token);

    String generateToken(SysUser account, UserDetails userDetails);

    LocalDateTime getExpiredTimeFromToken(String token);

    String generateRefreshToken(LoginRequest request);

    LoginRequest getLoginRequestFromToken(String token);

}
