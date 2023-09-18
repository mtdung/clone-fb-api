package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.report.CreateReportHoroRequest;
import vn.edu.fpt.horo.dto.request.report.CreateReportRequest;
import vn.edu.fpt.horo.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.horo.dto.response.report.CreateReportResponse;
import vn.edu.fpt.horo.dto.response.report.GetListReportResponse;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity.Rate;
import vn.edu.fpt.horo.entity.Report;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.ReportMapper;
import vn.edu.fpt.horo.repository.AdvisorRepository;
import vn.edu.fpt.horo.repository.ReportRepository;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * vn.edu.fpt.horo.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 30/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final AdvisorRepository advisorRepository;

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;
    private final FileService fileService;
    @Override
    public CreateReportResponse createReportAdvisor(CreateReportRequest request) {
        Advisor advisor = advisorRepository.findById(request.getAdvisorId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Advisor ID not exist"));
        Report report = Report.builder()
                .content(request.getContent())
                .imageReport(fileService.getFileById( request.getImageReport() ) )
                .reportTo(advisor)
                .status(false)
                .build();
        reportRepository.save(report);
        return CreateReportResponse.builder()
                .reportId(report.getReportId())
                .build();
    }

    @Override
    public Page<GetListReportResponse> getListReport(Pageable pageable,  String search) {
        if(search == null || search.isEmpty()){
            return reportRepository.findAll(pageable).map(reportMapper::mapGetListReport);
        }else {
            return reportRepository.findAllByCreatedBy_FullNameContaining(search, pageable).map(reportMapper::mapGetListReport);
        }
    }

    @Override
    public GetListReportResponse getReport(String reportId) {
        return reportMapper.mapGetListReport(reportRepository.findByReportId(reportId));
    }

    @Override
    public String setReportStatus(String reportId) {
        reportRepository.updateReportsStatus(reportId);
        return "success";
    }

    @Override
    public CreateReportResponse createReportHoro(CreateReportHoroRequest request) {
        Report report = Report.builder()
                .content(request.getContent())
                .status(false)
                .imageReport(fileService.getFileById( request.getImageReport() ) )
                .build();
        reportRepository.save(report);
        return CreateReportResponse.builder()
                .reportId(report.getReportId())
                .build();
    }
}
