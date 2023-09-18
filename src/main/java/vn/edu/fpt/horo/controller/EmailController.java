package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;

@RequestMapping("${app.application-context}/public/api/v1/email")
public interface EmailController {
    @PostMapping("/verify")
    ResponseEntity<GeneralResponse<Object>> sendVeryfyEmail(@RequestBody String email);

    @GetMapping("/verify")
    ResponseEntity<GeneralResponse<Object>> veryfyEmail(@RequestParam("email") String email);

    @PostMapping("/reset-password")
    ResponseEntity<GeneralResponse<Object>> resetPass(@RequestBody String username);
}
