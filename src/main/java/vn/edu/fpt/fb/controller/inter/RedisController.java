package vn.edu.fpt.fb.controller.inter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.request.RedisRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;

/**
 * @author namlh4
 */
@RequestMapping("${app.application-context}/${app.application-version}/${app.application-public}/redis")
public interface RedisController {
    @PostMapping("/manager")
    ResponseEntity<GeneralResponse<String>> manager(@RequestBody RedisRequest request);
}
