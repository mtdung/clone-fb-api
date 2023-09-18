package vn.edu.fpt.horo.dto.request.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vn.edu.fpt.horo.dto.request.report
 *
 * @author : Portgas.D.Ace
 * @created : 03/05/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateReportHoroRequest {
    private String imageReport;
    private String content;
}
