package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.common.factory.ResponseFactory;
import vn.edu.fpt.fb.controller.inter.SystemUserController;
import vn.edu.fpt.fb.dto.request.CreateUserRequest;
import vn.edu.fpt.fb.dto.request.ChangePasswordRequest;
import vn.edu.fpt.fb.dto.request.UpdateUserRequest;
import vn.edu.fpt.fb.service.inter.SystemUserService;

@RestController
@RequiredArgsConstructor
public class SystemUserControllerImpl implements SystemUserController {

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    SystemUserService systemUserService;

    @Override
    public ResponseEntity<GeneralResponse<String>> createUser(CreateUserRequest request) {
        String result = systemUserService.createUser(request);
        return responseFactory.response(result);
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> updateUser(UpdateUserRequest request) {
        String result = systemUserService.updateUser(request);
        return responseFactory.response(result);
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> lockUser(String userId) {
        String result = systemUserService.lockUser(userId);
        return responseFactory.response(result);
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> changePassword(ChangePasswordRequest request) {
        String result = systemUserService.changePassword(request);
        return responseFactory.response(result);
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> resetPassword(String userId) {
        String result = systemUserService.resetPassword(userId);
        return responseFactory.response(result);
    }
}
