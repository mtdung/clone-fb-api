package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.comment.ProfileResponseCustom;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity._PackageService;

import java.math.BigDecimal;
import java.util.List;

/**
 * vn.edu.fpt.horo.dto.response.account
 *
 * @author : Portgas.D.Ace
 * @created : 19/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InformationAdvisorFollowing {
    private String accountId;
    private String fullName;
    private String username;
    private String email;
    private Boolean isOnline;
    private ProfileResponseCustom profile;
    private String advisorId;
}
