package vn.edu.fpt.horo.dto.response.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.entity.Coin;
import vn.edu.fpt.horo.entity.Profile;
import vn.edu.fpt.horo.entity._Role;

import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAccountDetailResponse {
    private GetAdvisorResponse.AccountResponse account;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountResponse{
        private String accountId;
        private String fullName;
        private String username;
        private ProfileResponse profile;
    }

}
