package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;
import vn.edu.fpt.horo.entity._PackageService;

import java.math.BigDecimal;
import java.util.List;

/**
 * vn.edu.fpt.horo.dto.response.account
 *
 * @author : Portgas.D.Ace
 * @created : 03/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponse {
    private String accountId;
    private String fullName;
    private String username;
    private String email;
    private Coin coin;
    private ProfileResponse profile;
    private Boolean isOnline;
    private List<AdvisorResponse> following;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Coin {
        private String coinId;
        private BigDecimal amount;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AdvisorResponse {
        private String advisorId;
        private AccountAdvisorReponse account;
        private String summary;
        private String description;
        private List<GetServicePackageResponse> packageServices;
        private BigDecimal avgPrice;
        private Float totalRate;
        private Integer experience;
        private AdvisorStatus status;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountAdvisorReponse {
        private String accountId;
        private String fullName;
        private String username;
        private String email;
        private Coin coin;
        private ProfileResponse profile;
        private Boolean isOnline;
    }
}
