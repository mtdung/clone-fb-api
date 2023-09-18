package vn.edu.fpt.horo.dto.request.account;

import lombok.*;

import java.io.Serializable;

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
public class ConfirmResetPasswordRequest implements Serializable {

    private static final long serialVersionUID = -898982457398194518L;
    private String confirmPassword;
}
