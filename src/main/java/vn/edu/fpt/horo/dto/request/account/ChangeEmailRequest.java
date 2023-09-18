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
public class ChangeEmailRequest implements Serializable {

    private static final long serialVersionUID = 4443403409624077659L;
    private String newEmail;
}
