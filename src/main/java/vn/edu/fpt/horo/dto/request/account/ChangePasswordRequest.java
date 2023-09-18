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
public class ChangePasswordRequest implements Serializable {

    private static final long serialVersionUID = -5700374285227222490L;
    private String oldPassword;
    private String newPassword;
}
