package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.role.CreateRoleRequest;
import vn.edu.fpt.horo.dto.request.role.GetRoleRequest;
import vn.edu.fpt.horo.dto.request.role.UpdateRoleRequest;
import vn.edu.fpt.horo.dto.response.role.CreateRoleResponse;
import vn.edu.fpt.horo.dto.response.role.GetRoleResponse;
import vn.edu.fpt.horo.entity._Role;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.RoleRepository;
import vn.edu.fpt.horo.service.RoleService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.service.impl
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void init() {
        if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
            _Role adminRole = _Role.builder()
                    .roleName("ADMIN")
                    .description("Role for ADMIN")
                    .createdBy(null)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedBy(null)
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            try {
                roleRepository.save(adminRole);
                log.info("Init ADMIN role success");
            } catch (Exception ex) {
                throw new BusinessException("Can't init ADMIN role in database: " + ex.getMessage());
            }
        }
        if (roleRepository.findByRoleName("MANAGER").isEmpty()) {
            _Role managerRole = _Role.builder()
                    .roleName("MANAGER")
                    .description("Role for MANAGER")
                    .createdBy(null)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedBy(null)
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            try {
                roleRepository.save(managerRole);
                log.info("Init MANAGER role success");
            } catch (Exception ex) {
                throw new BusinessException("Can't init MANAGER role in database: " + ex.getMessage());
            }
        }
        if (roleRepository.findByRoleName("USER").isEmpty()) {
            _Role userRole = _Role.builder()
                    .roleName("USER")
                    .description("Role for USER")
                    .createdBy(null)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedBy(null)
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            try {
                roleRepository.save(userRole);
                log.info("Init USER role success");
            } catch (Exception ex) {
                throw new BusinessException("Can't init USER role in database: " + ex.getMessage());
            }
        }
    }

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        Optional<_Role> roleOptional = roleRepository.findByRoleName(request.getRoleName());
        if (roleOptional.isPresent()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role name already exist in database");
        }

        _Role role = _Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .build();

        try {
            role = roleRepository.save(role);
            log.info("Create role success: {}", role);
        } catch (Exception ex) {
            throw new BusinessException("Can't save new role to database: " + ex.getMessage());
        }
        return CreateRoleResponse.builder()
                .roleId(role.getRoleId())
                .build();
    }

    @Override
    public void updateRole(String roleId, UpdateRoleRequest request) {
        _Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role id not found"));

        if (Objects.nonNull(request.getRoleName())) {
            if (roleRepository.findByRoleName(request.getRoleName()).isPresent()) {
                throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role name already in database");
            }
            log.info("Update role name: {}", request.getRoleName());
            role.setRoleName(request.getRoleName());
        }
        if (Objects.nonNull(request.getDescription())) {
            log.info("Update description: {}", request.getDescription());
            role.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getIsEnable())) {
            log.info("Update is enable status: {}", request.getIsEnable());
            role.setIsEnable(request.getIsEnable());
        }
        try {
            roleRepository.save(role);
            log.info("Update role success");
        } catch (Exception ex) {
            throw new BusinessException("Can't save role in database when update role: " + ex.getMessage());
        }
    }

    @Override
    public void deleteRole(String roleId) {
        roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Role ID not found"));
        try {
            roleRepository.deleteById(roleId);
            log.info("Delete role: {} success", roleId);
        } catch (Exception ex) {
            throw new BusinessException("Can't delete role by ID: " + ex.getMessage());
        }
    }

    @Override
    public PageableResponse<GetRoleResponse> getRoleByCondition(GetRoleRequest request) {
        return null;
    }


}
