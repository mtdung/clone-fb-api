package vn.edu.fpt.horo.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class CreateRoleRequest implements Serializable {

    private static final long serialVersionUID = -7342949210860406239L;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$")
    private String roleName;
    private String description;
}
