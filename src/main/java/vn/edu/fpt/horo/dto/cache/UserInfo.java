package vn.edu.fpt.horo.dto.cache;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.cache
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"username", "email", "fullName", "avatar", "roles"})
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4689073615796931874L;
    private String username;
    private String email;
    private String fullName;
    private String avatar;
    private String accountId;
    private List<String> roles;
}
