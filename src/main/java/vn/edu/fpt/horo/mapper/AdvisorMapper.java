package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.edu.fpt.horo.dto.request.advisor.UpdateAdvisorRequest;

import vn.edu.fpt.horo.dto.response.account.InformationAccountResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity.AdvisorTicket;
import vn.edu.fpt.horo.service.FileService;


/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class, AccountMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdvisorMapper {

    GetAdvisorResponse mapToGetAdvisorDetailResponse(Advisor advisor);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    void updateAdvisor(@MappingTarget Advisor advisor, AdvisorTicket advisorTicket);

    void updateAdvisor(@MappingTarget Advisor advisor, UpdateAdvisorRequest request);

    GetAdvisorResponse mapAdvisorResponse(Advisor advisor);

    GetAdvisorByTopicResponse mapGetListAdvisorByTopic(Advisor advisor);

    InformationAccountResponse mapGetAccountFollower(Account account);
}
