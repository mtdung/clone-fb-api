package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.report.CreateReportHoroRequest;
import vn.edu.fpt.horo.dto.request.report.CreateReportRequest;
import vn.edu.fpt.horo.dto.response.report.CreateReportResponse;
import vn.edu.fpt.horo.dto.response.report.GetListReportResponse;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;

import java.util.List;

/**
 * vn.edu.fpt.horo.service
 *
 * @author : Portgas.D.Ace
 * @created : 30/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface ReportService {
    CreateReportResponse createReportAdvisor(CreateReportRequest request);


    Page<GetListReportResponse> getListReport(Pageable pageable, String search);

    GetListReportResponse getReport(String reportId);

    String setReportStatus(String reportId);

    CreateReportResponse createReportHoro(CreateReportHoroRequest request);
}
