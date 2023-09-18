package vn.edu.fpt.horo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.constant.AdvisorTicketType;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.admin.ApproveTicketRequest;
import vn.edu.fpt.horo.dto.request.CreateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.DeleteAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.UpdateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.response.advisor_ticket.CreateAdvisorTicketResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.DeleteAdvisorTicketResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.UpdateAdvisorTicketResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity.AdvisorTicket;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.AdvisorMapper;
import vn.edu.fpt.horo.mapper.AdvisorTicketMapper;
import vn.edu.fpt.horo.repository.*;
import vn.edu.fpt.horo.service.feign.NotificationFeignClient;
import vn.edu.fpt.horo.service.feign.SendNotificationRequest;

import java.util.List;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class AdvisorTicketService {

    private final AdvisorTicketRepository advisorTicketRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;
    private final TopicRepository topicRepository;
    private final AdvisorMapper advisorMapper;
    private final FileService fileService;
    private final AdvisorTicketMapper advisorTicketMapper;

    private final NotificationFeignClient notificationFeignClient;

    public CreateAdvisorTicketResponse requestCreateAdvisorTicket(String accountId, CreateAdvisorTicketRequest request){
        Account account = accountRepository.findAccountByAccountId(accountId)
                .orElseThrow();

        Advisor advisor = Advisor.builder()
                .cardId(request.getCardId())
                .account(account)
                .issuedPlace(request.getIssuedPlace())
                .issuedDate(request.getIssuedDate())
                .issuedBy(request.getIssuedBy())
                .summary(request.getSummary())
                .description(request.getDescription())
                .gender(request.getGender())
                .fontIdImage(fileService.getFileById(request.getFontIdImage()))
                .backIdImage(fileService.getFileById(request.getBackIdImage()))
                .portraitImage(fileService.getFileById(request.getPortraitImage()))
                .experience(request.getExperience())
                .build();
        advisor = advisorRepository.save(advisor);
        AdvisorTicket advisorTicket = advisorTicketMapper.mapAdvisorToTicket(advisor);
        advisorTicket.setAdvisor(advisor);
        advisorTicket.setType(AdvisorTicketType.REQUEST_CREATE);
        advisorTicket.setRequest(request.getRequest());

        advisorTicket = advisorTicketRepository.save(advisorTicket);
        return CreateAdvisorTicketResponse.builder()
                .advisorTicketId(advisorTicket.getTicketId())
                .advisorId(advisor.getAdvisorId())
                .build();
    }

    public void approveTicket(String ticketId, ApproveTicketRequest request) {
        AdvisorTicket advisorTicket = advisorTicketRepository.findFirstByTicketId(ticketId);
        advisorTicket.setTicketStatus(request.getIsApproved());
        advisorTicket.setResponse(request.getReason());

        switch (advisorTicket.getType()){
            case REQUEST_CREATE:
                auditRequestCreation(request, advisorTicket);
                break;
            case REQUEST_UPDATE:
                auditRequestUpdate(request, advisorTicket);
                break;
            case REQUEST_DELETE:
                auditRequestDeletion(request, advisorTicket);
                break;
            default:
                throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Invalid ticket type");
        }
//        notificationFeignClient.sendNotification(SendNotificationRequest.builder()
//                .userId(advisorTicket.getCreatedBy().getAccountId())
//                .title("Approved")
//                .body("Chuc mung ban da duoc phe duyet de tro thanh advisor")
//                .build());
    }

    private void auditRequestCreation(ApproveTicketRequest request, AdvisorTicket advisorTicket){
        Advisor advisor = advisorTicket.getAdvisor();
        if(Boolean.TRUE.equals(request.getIsApproved())){
            advisor.setStatus(AdvisorStatus.ACTIVE);
            advisorTicketRepository.save(advisorTicket);
            advisorRepository.save(advisor);
        }else{
            advisorTicket.setAdvisor(null);
            advisorTicketRepository.save(advisorTicket);
            advisorRepository.delete(advisor);
        }
    }

    private void auditRequestDeletion(ApproveTicketRequest request, AdvisorTicket advisorTicket){
        if(Boolean.TRUE.equals(request.getIsApproved())){
            Advisor advisor = advisorTicket.getAdvisor();
            advisor.setStatus(AdvisorStatus.ARCHIVED);
            advisorRepository.save(advisor);
        }
        advisorTicketRepository.save(advisorTicket);
    }

    private void auditRequestUpdate(ApproveTicketRequest request, AdvisorTicket advisorTicket){
        if(Boolean.TRUE.equals(request.getIsApproved())){
            Advisor advisor = advisorTicket.getAdvisor();
            advisorMapper.updateAdvisor(advisor, advisorTicket);
            advisorRepository.save(advisor);
        }
        advisorTicketRepository.save(advisorTicket);
    }

    public UpdateAdvisorTicketResponse requestUpdateAdvisorTicket(String advisorId, UpdateAdvisorTicketRequest request) {
       Advisor advisor = advisorRepository.findById(advisorId)
               .orElseThrow();
       AdvisorTicket advisorTicket = advisorTicketMapper.mapUpdateAdvisorToAdvisorTicket(request);
       advisorTicket.setAdvisor(advisor);
       advisorTicket.setType(AdvisorTicketType.REQUEST_UPDATE);
       advisorTicket.setRequest(request.getReason());
       advisorTicket = advisorTicketRepository.save(advisorTicket);
       return UpdateAdvisorTicketResponse.builder()
               .advisorTicketId(advisorTicket.getTicketId())
               .build();
    }
}
