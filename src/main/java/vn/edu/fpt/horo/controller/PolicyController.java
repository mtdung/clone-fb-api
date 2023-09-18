package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.policy.CreatePolicyRequest;
import vn.edu.fpt.horo.dto.response.policy.CreatePolicyResponse;
import vn.edu.fpt.horo.dto.response.policy.GetPolicyFileResponse;


/**
 * vn.edu.fpt.horo.controller
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequestMapping("${app.application-context}/public/api/v1/policy")
public interface PolicyController {
    @PostMapping
    ResponseEntity<GeneralResponse<CreatePolicyResponse>> createPolicy(@RequestBody CreatePolicyRequest request);

    @GetMapping("{policy-name}")
    ResponseEntity<GeneralResponse<GetPolicyFileResponse>> getPolicy(String policyName);

}
