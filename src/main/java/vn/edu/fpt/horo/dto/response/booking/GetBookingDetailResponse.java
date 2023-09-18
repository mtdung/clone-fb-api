package vn.edu.fpt.horo.dto.response.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.BookingType;
import vn.edu.fpt.horo.dto.response.account.GetAccountResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetBookingDetailResponse {
    private String bookingId;
    private GetAdvisorResponse advisor;
    private GetAccountResponse createdBy;
    private GetServicePackageResponse packageService;
    private BigDecimal coin;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private Integer slot;
    private BookingType type;
    private String reason;
    private String problem;
    private String totalBookings;
}
