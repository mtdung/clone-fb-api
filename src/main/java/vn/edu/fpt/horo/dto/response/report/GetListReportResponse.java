package vn.edu.fpt.horo.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.dto.response.report
 *
 * @author : Portgas.D.Ace
 * @created : 02/05/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetListReportResponse {
    private String reportId;
    private AccountResponse createdBy;
    private AdvisorResponse reportTo;
    private String content;
    private FileResponse imageReport;
    private Boolean status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AdvisorResponse {
        private AccountResponse account;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountResponse {
        private String username;
        private String fullName;
    }

}
