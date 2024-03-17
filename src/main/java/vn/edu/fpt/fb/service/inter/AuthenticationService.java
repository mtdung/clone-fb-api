package vn.edu.fpt.fb.service.inter;

import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
}
