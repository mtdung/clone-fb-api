package vn.edu.fpt.horo.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.response.role
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateRoleResponse implements Serializable {

    private static final long serialVersionUID = 1566888192937906628L;
    private String roleId;
}
