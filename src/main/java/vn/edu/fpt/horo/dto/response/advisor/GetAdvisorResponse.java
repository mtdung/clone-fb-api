package vn.edu.fpt.horo.dto.response.advisor;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity.Account;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
public class GetAdvisorResponse implements Serializable {
    private static final long serialVersionUID = 8174572376322355149L;
    private String advisorId;
    private AccountResponse account;
    private List<TopicResponse> topics;
    private BigDecimal avgPrice;
    private Float totalRate;
    private List<_PackageServiceResponse> packageServices;
    private AdvisorStatus status;
    private Integer experience;
    private String description;
    private String summary;
    private List<RateResponse> rates;
    private List<AccountResponse> followers;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class _PackageServiceResponse{
        private String packageServiceId;
        private String serviceName;
        private String description;
        private TopicResponse topic;
        private BigDecimal price;
        private Boolean isActive;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class TopicResponse{
        private String topicId ;
        private String topicName;
        private FileResponse imageTopic;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountResponse{
        private String accountId;
        private String fullName;
        private String username;
        private ProfileResponse profile;
        private Boolean isOnline;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class RateResponse{
        private String rateId;
        private Float point;
        private RateByResponse rateBy;
        private String content;
        private LocalDateTime createdDate;
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @Builder
        public static class RateByResponse{
            private String accountId;
            private String fullName;
            private String username;
            private ProfileRateBy profile;

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            @Builder
            public static class ProfileRateBy {
                private FileResponse avatar;
            }
        }


    }
}
