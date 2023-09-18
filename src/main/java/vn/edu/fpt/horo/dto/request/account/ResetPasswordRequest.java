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
public class ResetPasswordRequest implements Serializable {

    private static final long serialVersionUID = -5219107503526276069L;
    private String emailOrUsername;
}
