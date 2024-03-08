package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailControllerImpl implements EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<Object>> sendVeryfyEmail(String email) {
        emailService.sendVerifyEmail(email);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> veryfyEmail(String email) {
        return responseFactory.response(emailService.verifyEmail(email));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> resetPass(String username) {
        return responseFactory.response(emailService.resetPassword(username));
    }
}
