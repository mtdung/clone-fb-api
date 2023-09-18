package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.dto.request.advisor.UpdateAdvisorRequest;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;
import vn.edu.fpt.horo.dto.response.account.InformationAccountResponse;
import vn.edu.fpt.horo.dto.response.advisor.AdvisorFreeTimeResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public interface AdvisorService {

    GetAdvisorResponse getAdvisorDetail(String advisorId);

    Page<GetAdvisorResponse> getAdvisor(String name, Integer page, Integer size);

    List<GetServicePackageResponse> getAdvisorServicePackage(String advisorId);

    void updateAdvisor(String advisorId, UpdateAdvisorRequest request);

    AdvisorFreeTimeResponse getFreeTime(String advisorId, LocalDate date);

    List<GetAdvisorByTopicResponse> getListAdvisorByTopic(String topicId);

    Boolean checkIsAdvisor(String accountId);

    List<GetAdvisorByTopicResponse> searchAdvisorByName(String advisorName);

    Page<AccountResponse> getAccountFollower(String advisorId, Pageable pageable);

    String deleteAdvisor(String advisorId);
}
