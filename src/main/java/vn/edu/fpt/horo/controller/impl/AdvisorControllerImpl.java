package vn.edu.fpt.horo.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.AdvisorController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.UpdateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.advisor.UpdateAdvisorRequest;
import vn.edu.fpt.horo.dto.response.advisor.AdvisorFreeTimeResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.UpdateAdvisorTicketResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.AdvisorService;
import vn.edu.fpt.horo.service.AdvisorTicketService;

import java.time.LocalDate;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class AdvisorControllerImpl implements AdvisorController {

    private final ResponseFactory responseFactory;
    private final AdvisorService advisorService;
    private final AdvisorTicketService advisorTicketService;

    @Override
    public ResponseEntity<GeneralResponse<Page<GetAdvisorResponse>>> getAdvisor(String name, Integer page, Integer size) {
        return responseFactory.response(advisorService.getAdvisor(name, page, size));
    }

    @Override
    public ResponseEntity<GeneralResponse<List<GetAdvisorByTopicResponse>>> searchAdvisorByName(String advisorName) {
        return responseFactory.response(advisorService.searchAdvisorByName(advisorName));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateAdvisor(String advisorId, UpdateAdvisorRequest request) {
        advisorService.updateAdvisor(advisorId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<List<GetServicePackageResponse>>> getAdvisorServicePackage(String advisorId) {
        return responseFactory.response(advisorService.getAdvisorServicePackage(advisorId));
    }

    @Override
    public ResponseEntity<GeneralResponse<List<GetAdvisorByTopicResponse>>> getListAdvisorByTopic(String topicId) {
        return responseFactory.response(advisorService.getListAdvisorByTopic(topicId));
    }

    @Override
    public ResponseEntity<GeneralResponse<String>> requestDeleteAdvisor(String advisorId) {
        return responseFactory.response(advisorService.deleteAdvisor(advisorId));
    }

    @Override
    public ResponseEntity<GeneralResponse<UpdateAdvisorTicketResponse>> requestUpdateAdvisor(String advisorId, UpdateAdvisorTicketRequest request) {
        return responseFactory.response(advisorTicketService.requestUpdateAdvisorTicket(advisorId, request));
    }

    @Override
    public ResponseEntity<GeneralResponse<AdvisorFreeTimeResponse>> getAdvisorFreeTime(String advisorId, LocalDate date) {
        return responseFactory.response(advisorService.getFreeTime(advisorId, date));
    }
}
