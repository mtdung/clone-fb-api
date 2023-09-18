package vn.edu.fpt.horo.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.AuditableRequest;
import vn.edu.fpt.horo.utils.RequestDataUtils;

/**
 * vn.edu.fpt.accounts.dto.request.role
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@SuperBuilder
public class GetRoleRequest extends AuditableRequest {

    private static final long serialVersionUID = 636570691925831402L;
    private String roleId;
    private String roleName;
    private String description;
    private Boolean isEnable;

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return RequestDataUtils.convertSearchableData(roleName);
    }

    public String getDescription() {
        return RequestDataUtils.convertSearchableData(description);
    }

    public Boolean getIsEnable() {
        return RequestDataUtils.convertSearchableData(isEnable);
    }
}
