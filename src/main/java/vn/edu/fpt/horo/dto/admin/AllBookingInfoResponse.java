package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.BookingType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AllBookingInfoResponse {

    private String bookingId = UUID.randomUUID().toString();
    private AccountResponse createdBy;
    private BigDecimal coin;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private Integer slot;
    private BookingType type;
    private AdvisorResponse advisor;
    private PackageServiceReponse packageService;
    private String reason;
    private String problem;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class PackageServiceReponse {
        private String serviceName;
        private TopicResponse topic;
        private BigDecimal price;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class TopicResponse {
        private String topicName;
    }

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
        private String fullName;
        private ProfileResponse profile;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ProfileResponse {
        private String phoneNumber;
    }
}
