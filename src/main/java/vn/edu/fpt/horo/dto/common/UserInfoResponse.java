package vn.edu.fpt.horo.dto.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.cache.UserInfo;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"accountId", "userInfo"})
public class UserInfoResponse implements Serializable {

    private static final long serialVersionUID = 2360096410069646348L;
    private String accountId;
    private UserInfo userInfo;
}
