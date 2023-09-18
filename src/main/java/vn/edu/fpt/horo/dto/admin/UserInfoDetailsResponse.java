package vn.edu.fpt.horo.dto.admin;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoDetailsResponse {
    private String accountId = UUID.randomUUID().toString();
    private String email;
    private String fullName;
    private LocalDateTime createdDate;
    private ProfileResponse profile;
    private CoinResponse coin;
    private int successBooking;
    private int failureBooking;

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
        private String gender;
        private LocalDateTime dateOfBirth;
        private String address;
        private String phoneNumber;
    }
}
