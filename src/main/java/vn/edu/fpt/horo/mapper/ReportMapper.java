package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.response.report.GetListReportResponse;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;
import vn.edu.fpt.horo.entity.Report;
import vn.edu.fpt.horo.entity.Topic;

/**
 * vn.edu.fpt.horo.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 02/05/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring")
public interface ReportMapper {
    GetListReportResponse mapGetListReport(Report report);

}
