package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.response.account
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateAccountResponse implements Serializable {

    private static final long serialVersionUID = 6362275297713845544L;
    private String accountId;
}
