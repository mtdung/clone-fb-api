package vn.edu.fpt.horo.dto.request.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.request.account
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
