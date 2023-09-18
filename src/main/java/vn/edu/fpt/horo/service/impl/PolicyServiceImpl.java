package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.policy.CreatePolicyRequest;
import vn.edu.fpt.horo.dto.response.policy.CreatePolicyResponse;
import vn.edu.fpt.horo.dto.response.policy.GetPolicyFileResponse;
import vn.edu.fpt.horo.entity.Policy;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.PolicyMapper;
import vn.edu.fpt.horo.repository.PolicyRepository;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.service.PolicyService;


/**
 * vn.edu.fpt.horo.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private final PolicyMapper policyMapper;

    private final PolicyRepository policyRepository;
    private final FileService fileService;
    @Override
    public CreatePolicyResponse createPolicy(CreatePolicyRequest request) {
        Policy policy = policyMapper.mapCreatePolicyMapper(request);
        try {
            policy = policyRepository.save(policy);
            log.info("Save policy success");
        }catch (Exception ex){
            log.error("Error when save policy to database: {}", ex.getMessage());
            throw new BusinessException("Can't save policy to database: "+ ex.getMessage());
        }
        return CreatePolicyResponse.builder()
                .policyId(policy.getPolicyId())
                .build();
    }

    @Override
    public GetPolicyFileResponse getPolicy(String policyName) {
        Policy policy = policyRepository.findByPolicyName(policyName)
                .orElseThrow(()-> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Policy not exist"));
        return GetPolicyFileResponse.builder().pdfFaqResponse(fileService.getFileResponse(policy.getFilePolicy())).build();
    }


}
