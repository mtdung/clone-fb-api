package vn.edu.fpt.fb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.request.account
 *
 * @author : Portgas.D.Ace
 * @created : 12/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest implements Serializable {

    private String emailOrUsername;
    private String password;
    private Boolean isAdmin;
}
