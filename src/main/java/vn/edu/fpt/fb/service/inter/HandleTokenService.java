package vn.edu.fpt.fb.service.inter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.entity.SysRole;
import vn.edu.fpt.fb.entity.SysUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HandleTokenService {

    Optional<Authentication> getAuthenticationFromToken(String token);

    String generateToken(SysUser account, List<SysRole> sysRoles, UserDetails userDetails);

    LocalDateTime getExpiredTimeFromToken(String token);

    String generateRefreshToken(LoginRequest request);

    LoginRequest getLoginRequestFromToken(String token);

}
