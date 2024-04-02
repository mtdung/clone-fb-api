package vn.edu.fpt.fb.controller.inter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;

@RequestMapping("${app.application-context}/${app.application-version}/${app.application-public}/authen")
public interface AuthenticationController {

    @PostMapping("/login")
    ResponseEntity<GeneralResponse<LoginResponse>> login(@RequestBody LoginRequest request);

}
