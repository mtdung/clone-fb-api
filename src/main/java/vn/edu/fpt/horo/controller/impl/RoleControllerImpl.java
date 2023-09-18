package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.RoleController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.common.SortableRequest;
import vn.edu.fpt.horo.dto.request.role.CreateRoleRequest;
import vn.edu.fpt.horo.dto.request.role.GetRoleRequest;
import vn.edu.fpt.horo.dto.request.role.UpdateRoleRequest;
import vn.edu.fpt.horo.dto.response.role.CreateRoleResponse;
import vn.edu.fpt.horo.dto.response.role.GetRoleResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.RoleService;

import java.util.ArrayList;
import java.util.Objects;

/**
 * vn.edu.fpt.accounts.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<CreateRoleResponse>> createRole(CreateRoleRequest request) {
        return responseFactory.response(roleService.createRole(request), ResponseStatusEnum.CREATED);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateRole(String roleId, UpdateRoleRequest request) {
        roleService.updateRole(roleId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> deleteRoleById(String roleId) {
        roleService.deleteRole(roleId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<PageableResponse<GetRoleResponse>>> getRoleByCondition(
            String roleId,
            String roleIdSortBy,
            String roleName,
            String roleNameSortBy,
            String description,
            String descriptionSortBy,
            Boolean isEnable,
            String isEnableSortBy,
            String createdBy,
            String createdBySortBy,
            String createdDateFrom,
            String createdDateTo,
            String createdDateSortBy,
            String lastModifiedBy,
            String lastModifiedBySortBy,
            String lastModifiedDateFrom,
            String lastModifiedDateTo,
            String lastModifiedDateSortBy) {
        java.util.List<SortableRequest> sortableRequests = new ArrayList<>();
        if(Objects.nonNull(roleIdSortBy)){
            sortableRequests.add(new SortableRequest("_id", roleIdSortBy));
        }
        if(Objects.nonNull(roleNameSortBy)){
            sortableRequests.add(new SortableRequest("role_name", roleNameSortBy));
        }
        if(Objects.nonNull(descriptionSortBy)){
            sortableRequests.add(new SortableRequest("description", descriptionSortBy));
        }
        if(Objects.nonNull(isEnableSortBy)){
            sortableRequests.add(new SortableRequest("is_enable", isEnableSortBy));
        }
        if(Objects.nonNull(createdBySortBy)){
            sortableRequests.add(new SortableRequest("created_by", createdBySortBy));
        }
        if(Objects.nonNull(createdDateSortBy)){
            sortableRequests.add(new SortableRequest("created_date", createdDateSortBy));
        }
        if(Objects.nonNull(lastModifiedBySortBy)){
            sortableRequests.add(new SortableRequest("last_modified_by", lastModifiedBySortBy));
        }
        if(Objects.nonNull(lastModifiedDateSortBy)){
            sortableRequests.add(new SortableRequest("last_modified_date", lastModifiedDateSortBy));
        }

        GetRoleRequest request = GetRoleRequest.builder()
                .roleId(roleId)
                .roleName(roleName)
                .description(description)
                .isEnable(isEnable)
                .createdBy(createdBy)
                .createdDateFrom(createdDateFrom)
                .createdDateTo(createdDateTo)
                .lastModifiedBy(lastModifiedBy)
                .lastModifiedDateFrom(lastModifiedDateFrom)
                .lastModifiedDateTo(lastModifiedDateTo)
                .sortBy(sortableRequests)
                .build();
        return responseFactory.response(roleService.getRoleByCondition(request));
    }


}
