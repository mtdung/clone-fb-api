package vn.edu.fpt.horo.dto.response.advisor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.comment.ProfileResponseCustom;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.response.advisor
 *
 * @author : Portgas.D.Ace
 * @created : 15/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAdvisorByTopicResponse implements Serializable {

    private String advisorId;
    private Integer experience;
    private Float totalRate;
    private AccountResponse account;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountResponse{
        private String accountId;
        private String fullName;
        private String username;
        private ProfileResponseCustom profile;
    }

}
