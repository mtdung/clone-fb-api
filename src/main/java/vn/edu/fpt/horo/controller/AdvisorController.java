package vn.edu.fpt.horo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.UpdateAdvisorTicketRequest;
import vn.edu.fpt.horo.dto.request.advisor.UpdateAdvisorRequest;
import vn.edu.fpt.horo.dto.response.advisor.AdvisorFreeTimeResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.advisor_ticket.UpdateAdvisorTicketResponse;
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
@RequestMapping("${app.application-context}/public/api/v1/advisories")
public interface AdvisorController {

    @GetMapping
    ResponseEntity<GeneralResponse<Page<GetAdvisorResponse>>> getAdvisor(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    );

    @GetMapping("/search")
    ResponseEntity<GeneralResponse<List<GetAdvisorByTopicResponse>>> searchAdvisorByName(@RequestParam(name = "advisor-name", required = false) String advisorName);

    @PutMapping("/{advisor-id}")
    ResponseEntity<GeneralResponse<Object>> updateAdvisor(@PathVariable(name = "advisor-id") String advisorId, @RequestBody UpdateAdvisorRequest request);

    @GetMapping("/{advisor-id}/service-package")
    ResponseEntity<GeneralResponse<List<GetServicePackageResponse>>> getAdvisorServicePackage(@PathVariable(name = "advisor-id") String advisorId);

    @GetMapping("/{topic-id}/advisors")
    ResponseEntity<GeneralResponse<List<GetAdvisorByTopicResponse>>> getListAdvisorByTopic(@PathVariable(name = "topic-id") String topicId);

    @PostMapping("/{advisor-id}/delete-advisor")
    ResponseEntity<GeneralResponse<String>> requestDeleteAdvisor(@PathVariable("advisor-id") String advisorId);

    @PostMapping("/{advisor-id}/update-advisor")
    ResponseEntity<GeneralResponse<UpdateAdvisorTicketResponse>> requestUpdateAdvisor(@PathVariable("advisor-id") String advisorId, @RequestBody UpdateAdvisorTicketRequest request);

    @GetMapping("/{advisor-id}/free")
    @Operation(description = "Lấy danh sách các slot rảnh trong ngày(TRUE - rảnh rỗi, FALSE - bận)")
    ResponseEntity<GeneralResponse<AdvisorFreeTimeResponse>> getAdvisorFreeTime(
            @PathVariable(name = "advisor-id") String advisorId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(name = "in-date") LocalDate date
    );
}
