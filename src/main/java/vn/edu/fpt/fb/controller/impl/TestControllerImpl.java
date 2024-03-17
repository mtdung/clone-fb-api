package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.common.factory.ResponseFactory;
import vn.edu.fpt.fb.controller.inter.TestController;

/**
 * @author namlh4
 */
@RestController
@RequiredArgsConstructor
public class TestControllerImpl implements TestController {

    @Autowired
    ResponseFactory responseFactory;
    @Override
    public ResponseEntity<GeneralResponse<String>> testAdmin() {
        return responseFactory.response("SUCCESS");
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> testUser() {
        return responseFactory.response("SUCCESS");
    }
}
