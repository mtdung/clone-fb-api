package vn.edu.fpt.fb.controller.inter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.fb.common.annotation.IsAdmin;
import vn.edu.fpt.fb.common.annotation.IsUser;
import vn.edu.fpt.fb.common.factory.GeneralResponse;

/**
 * @author namlh4
 */
@RequestMapping("${app.application-context}/${app.application-version}/test")
public interface TestController {
    @PostMapping("/admin")
    @IsAdmin
    ResponseEntity<GeneralResponse<String>> testAdmin();

    @PostMapping("/user")
    @IsUser
    ResponseEntity<GeneralResponse<String>> testUser();

}
