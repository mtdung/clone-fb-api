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
public class CreateAccountRequest implements Serializable {

    private static final long serialVersionUID = 4537627065554488514L;
    private String email;
    private String username;
    private String fullName;
    private String password;
}
