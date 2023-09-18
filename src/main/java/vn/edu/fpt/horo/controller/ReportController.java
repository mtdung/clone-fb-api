package vn.edu.fpt.horo.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.report.CreateReportHoroRequest;
import vn.edu.fpt.horo.dto.request.report.CreateReportRequest;
import vn.edu.fpt.horo.dto.response.report.CreateReportResponse;
import vn.edu.fpt.horo.dto.response.report.GetListReportResponse;

/**
 * vn.edu.fpt.horo.controllerk, hikn hotl6=[yo 65
 *
 * @author : Portgas.D.Ace
 * @created : 30/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequestMapping("${app.application-context}/public/api/v1/reports")
public interface ReportController {
    @PostMapping("/report")
    ResponseEntity<GeneralResponse<CreateReportResponse>> createReportAdvisor(@RequestBody CreateReportRequest request);

    @PostMapping("/horos")
    ResponseEntity<GeneralResponse<CreateReportResponse>> createReportHoro(@RequestBody CreateReportHoroRequest request);

    @GetMapping
    ResponseEntity<GeneralResponse<Page<GetListReportResponse>>> getListReport(@ParameterObject Pageable pageable,@RequestParam String search);

    @GetMapping("/detail")
    ResponseEntity<GeneralResponse<GetListReportResponse>> getReport(@RequestParam("report_id") String reportId);

    @PutMapping("/detail")
    ResponseEntity<GeneralResponse<Object>> setReport(@RequestParam("report_id") String reportId);
}
