package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.response.account
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResetPasswordResponse implements Serializable {

    private static final long serialVersionUID = -8278518429130161484L;
    private String newPassword;
}
