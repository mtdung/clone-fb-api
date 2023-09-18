package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.policy.CreatePolicyRequest;
import vn.edu.fpt.horo.dto.response.policy.CreatePolicyResponse;
import vn.edu.fpt.horo.dto.response.policy.GetPolicyFileResponse;

/**
 * vn.edu.fpt.horo.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface PolicyService {
    CreatePolicyResponse createPolicy(CreatePolicyRequest request);

    GetPolicyFileResponse getPolicy(String policyName);
}
