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
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"memberId", "accountId", "role", "userInfo"})
public class MemberInfoResponse implements Serializable {

    private static final long serialVersionUID = -6809375447228144572L;
    private String memberId;
    private String accountId;
    private String role;
    private UserInfo userInfo;


}
