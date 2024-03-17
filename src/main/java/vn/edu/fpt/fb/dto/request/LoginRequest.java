package vn.edu.fpt.fb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest implements Serializable {

    private String emailOrUsernameOrPhoneNumber;
    private String password;
    private Boolean isAdmin;
}
