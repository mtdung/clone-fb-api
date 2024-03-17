package vn.edu.fpt.fb.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime tokenExpireTime;
    private LocalDateTime refreshTokenExpireTime;
}