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
public class AddRoleToAccountRequest implements Serializable {

    private static final long serialVersionUID = -7456957076843225792L;
    private String roleId;
}
