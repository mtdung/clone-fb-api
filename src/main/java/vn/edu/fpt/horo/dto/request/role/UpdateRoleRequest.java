package vn.edu.fpt.horo.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.request.role
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateRoleRequest implements Serializable {

    private static final long serialVersionUID = 8589171198169610095L;
    private String roleName;
    private String description;
    private Boolean isEnable;
}
