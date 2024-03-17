package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.common.factory.ResponseFactory;
import vn.edu.fpt.fb.controller.inter.AuthenticationController;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;
import vn.edu.fpt.fb.service.inter.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    public ResponseEntity<GeneralResponse<LoginResponse>> login(LoginRequest request) {
        return responseFactory.response(authenticationService.login(request));
    }
}
