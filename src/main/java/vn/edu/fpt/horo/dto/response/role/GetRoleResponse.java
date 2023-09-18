package vn.edu.fpt.horo.dto.response.role;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.AuditableResponse;
import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.response.role
 **/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@JsonPropertyOrder({"roleId", "roleName", "description", "isEnable", "status", "permissions"})
public class GetRoleResponse extends AuditableResponse implements Serializable {

    private static final long serialVersionUID = 1509654297902859049L;
    private String roleId;
    private String roleName;
    private String description;
    private Boolean isEnable;
}
