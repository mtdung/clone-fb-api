package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.role.CreateRoleRequest;
import vn.edu.fpt.horo.dto.request.role.GetRoleRequest;
import vn.edu.fpt.horo.dto.request.role.UpdateRoleRequest;
import vn.edu.fpt.horo.dto.response.role.CreateRoleResponse;
import vn.edu.fpt.horo.dto.response.role.GetRoleResponse;
import vn.edu.fpt.horo.entity._Role;

/**
 * vn.edu.fpt.accounts.service
 **/

public interface RoleService {

    void init();

    CreateRoleResponse createRole(CreateRoleRequest request);

    void updateRole(String roleId, UpdateRoleRequest request);

    void deleteRole(String roleId);

    PageableResponse<GetRoleResponse> getRoleByCondition(GetRoleRequest request);

}
