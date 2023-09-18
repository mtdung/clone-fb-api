package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.request.UpdateAdvisorTicketRequest;
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
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface AdvisorTicketMapper {

    AdvisorTicket mapAdvisorToTicket(Advisor advisor);

    AdvisorTicket mapUpdateAdvisorToAdvisorTicket(UpdateAdvisorTicketRequest request);
}
