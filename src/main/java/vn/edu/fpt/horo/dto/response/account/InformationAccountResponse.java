package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.comment.ProfileResponseCustom;

/**
 * vn.edu.fpt.horo.dto.response.account
 *
 * @author : Portgas.D.Ace
 * @created : 20/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InformationAccountResponse {
    private String accountId;
    private String fullName;
    private String username;
    private String email;
    private Boolean isOnline;
    private ProfileResponseCustom profile;
}
