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
public class RefreshTokenRequest implements Serializable {

    private static final long serialVersionUID = 4816939672245136335L;
    private String refreshToken;
}
