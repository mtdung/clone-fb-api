package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.report.CreateReportHoroRequest;
import vn.edu.fpt.fb.dto.request.report.CreateReportRequest;
import vn.edu.fpt.fb.dto.response.report.CreateReportResponse;
import vn.edu.fpt.fb.dto.response.report.GetListReportResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

/**
 * vn.edu.fpt.horo.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 02/05/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
public class ReportControllerImpl implements ReportController  {
    private final ResponseFactory responseFactory;

    private final ReportService reportService;

    @Override
    public ResponseEntity<GeneralResponse<CreateReportResponse>> createReportAdvisor(CreateReportRequest request) {
        return responseFactory.response(reportService.createReportAdvisor(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<CreateReportResponse>> createReportHoro(CreateReportHoroRequest request) {
        return responseFactory.response(reportService.createReportHoro(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetListReportResponse>>> getListReport(Pageable pageable, String search) {
        return responseFactory.response(reportService.getListReport(pageable, search));
    }

    @Override
    public ResponseEntity<GeneralResponse<GetListReportResponse>> getReport(String reportId) {
        return responseFactory.response(reportService.getReport(reportId));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> setReport(String reportId) {
        return responseFactory.response(reportService.setReportStatus(reportId));
    }
}
