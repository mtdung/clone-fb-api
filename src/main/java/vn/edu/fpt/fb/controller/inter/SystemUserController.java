package vn.edu.fpt.fb.controller.inter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.fb.common.annotation.IsUser;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.dto.request.CreateUserRequest;
import vn.edu.fpt.fb.dto.request.ResetPasswordRequest;
import vn.edu.fpt.fb.dto.request.UpdateUserRequest;

@RequestMapping("${app.application-context}/${app.application-version}/${app.application-private}/user")
public interface SystemUserController {

    @PostMapping("/create")
    ResponseEntity<GeneralResponse<String>> createUser(@RequestBody CreateUserRequest request);

    @PostMapping("/update")
    @IsUser
    ResponseEntity<GeneralResponse<String>> updateUser(@RequestBody UpdateUserRequest request);

    @PostMapping("/lock")
    @IsUser
    ResponseEntity<GeneralResponse<String>> lockUser(@RequestParam String userId);

    @PostMapping("/reset-password")
    @IsUser
    ResponseEntity<GeneralResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request);
}
