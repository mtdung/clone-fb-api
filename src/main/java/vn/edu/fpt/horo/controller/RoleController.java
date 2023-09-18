package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.role.CreateRoleRequest;
import vn.edu.fpt.horo.dto.request.role.UpdateRoleRequest;
import vn.edu.fpt.horo.dto.response.role.CreateRoleResponse;
import vn.edu.fpt.horo.dto.response.role.GetRoleResponse;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RequestMapping("${app.application-context}/public/api/v1/roles")
public interface RoleController {

    @PostMapping("/role")
    ResponseEntity<GeneralResponse<CreateRoleResponse>> createRole(@Validated @RequestBody CreateRoleRequest request);

    @PutMapping("/{role-id}")
    ResponseEntity<GeneralResponse<Object>> updateRole(
            @PathVariable("role-id") String roleId,
            @RequestBody UpdateRoleRequest request
    );

    @DeleteMapping("/{role-id}")
    ResponseEntity<GeneralResponse<Object>> deleteRoleById(@PathVariable("role-id") String roleId);

    @GetMapping
    ResponseEntity<GeneralResponse<PageableResponse<GetRoleResponse>>> getRoleByCondition(
            @RequestParam(name = "role-id", required = false) String roleId,
            @RequestParam(name = "role-id-sort-by", required = false) String roleIdSortBy,
            @RequestParam(name = "role-name", required = false) String roleName,
            @RequestParam(name = "role-name-sort-by", required = false) String roleNameSortBy,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "description-sort-by", required = false) String descriptionSortBy,
            @RequestParam(name = "is-enable", required = false) Boolean isEnable,
            @RequestParam(name = "is-enable-sort-by", required = false) String isEnableSortBy,
            @RequestParam(name = "created-by", required = false) String createdBy,
            @RequestParam(name = "created-by-sort-by", required = false) String createdBySortBy,
            @RequestParam(name = "created-date-from", required = false) String createdDateFrom,
            @RequestParam(name = "created-date-to", required = false) String createdDateTo,
            @RequestParam(name = "created-date-sort-by", required = false) String createdDateSortBy,
            @RequestParam(name = "last-modified-by", required = false) String lastModifiedBy,
            @RequestParam(name = "last-modified-by-sort-by", required = false) String lastModifiedBySortBy,
            @RequestParam(name = "last-modified-date-from", required = false) String lastModifiedDateFrom,
            @RequestParam(name = "last-modified-date-to", required = false) String lastModifiedDateTo,
            @RequestParam(name = "last-modified-date-sort-by", required = false) String lastModifiedDateSortBy
    );

}
