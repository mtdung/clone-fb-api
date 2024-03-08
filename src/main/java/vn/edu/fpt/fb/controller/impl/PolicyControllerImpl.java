package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.policy.CreatePolicyRequest;
import vn.edu.fpt.fb.dto.response.policy.CreatePolicyResponse;
import vn.edu.fpt.fb.dto.response.policy.GetPolicyFileResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

/**
 * vn.edu.fpt.horo.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class PolicyControllerImpl implements PolicyController {
    private final ResponseFactory responseFactory;

    private final PolicyService policyService;

    @Override
    public ResponseEntity<GeneralResponse<CreatePolicyResponse>> createPolicy(CreatePolicyRequest request) {
        return responseFactory.response(policyService.createPolicy(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<GetPolicyFileResponse>> getPolicy(String policyName) {
        return responseFactory.response(policyService.getPolicy(policyName));
    }

}