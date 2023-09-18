package vn.edu.fpt.horo.dto.admin;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AllUserInfoResponse {
    private String accountId = UUID.randomUUID().toString();
    private String email;
    private String fullName;
    private LocalDateTime createdDate;
    private CoinResponse coin;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class CoinResponse {
        private BigDecimal amount;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ProfileResponse {
        private LocalDateTime dateOfBirth;
        private String phoneNumber;
        private String description;
    }
}
