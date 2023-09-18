package vn.edu.fpt.horo.dto.response.account;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.response.account
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 8619678507118001064L;
    private String accountId;
    private String username;
    private String email;
    private String fullName;
    private String token;
    private String refreshToken;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private LocalDateTime tokenExpireTime;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private LocalDateTime refreshTokenExpireTime;
}
