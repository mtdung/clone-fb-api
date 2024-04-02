package vn.edu.fpt.fb.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.common.constant.StatusConstant.StatusUser;
import vn.edu.fpt.fb.common.constant.TypeConstant;
import vn.edu.fpt.fb.dto.request.CreateUserRequest;
import vn.edu.fpt.fb.dto.request.UpdateUserRequest;
import vn.edu.fpt.fb.entity.SysRole;
import vn.edu.fpt.fb.entity.SysUser;
import vn.edu.fpt.fb.entity.UserRole;
import vn.edu.fpt.fb.repository.ProfileRepo;
import vn.edu.fpt.fb.repository.SysRoleRepo;
import vn.edu.fpt.fb.repository.SysUserRepo;
import vn.edu.fpt.fb.repository.UserRoleRepo;
import vn.edu.fpt.fb.service.inter.HandleTokenService;
import vn.edu.fpt.fb.service.inter.SystemUserService;
import vn.edu.fpt.fb.utils.AuditorUtils;
import vn.edu.fpt.fb.utils.Utils;

import java.util.Optional;

import static vn.edu.fpt.fb.common.constant.Constant.*;
import static vn.edu.fpt.fb.utils.RequestDataUtils.isNullOrEmptyString;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    HandleTokenService tokenService;
    @Autowired
    SysUserRepo sysUserRepo;
    @Autowired
    SysRoleRepo sysRoleRepo;
    @Autowired
    UserRoleRepo userRoleRepo;
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
     Gson gson = new Gson();

    @Override
    public String createUser(CreateUserRequest req) {
        log.info("Request CreateUser: {}",gson.toJson(req));
        //validate
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                USERNAME_OR_PASS_EMPTY,
                isNullOrEmptyString(req.getUserName(), req.getPassword(), req.getEmail(), req.getPhoneNumber()));
        Optional<SysUser> sysUserOtp = sysUserRepo.findByEmailOrUsernameOrPhoneNumber(req.getEmail(), req.getUserName(), req.getPhoneNumber());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                USERNAME_EMAIL_PHONE_EXIST,
                sysUserOtp.isPresent());
        Optional<SysRole> sysRoleOtp = sysRoleRepo.findById(req.getRoleId());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ROLE_NOT_EXIST,
                sysRoleOtp.isEmpty());
        //insert
        SysUser sysUser = SysUser.builder()
                .username(req.getUserName())
                .email(req.getEmail())
                .phoneNumber(req.getPhoneNumber())
                .password(encoder.encode(req.getPassword()))
                .status(StatusUser.STATUS_ACTIVE)
                .userType(TypeConstant.UserType.TYPE_NORMAL)
                .build();

        SysUser savedUser = sysUserRepo.save(sysUser);
        UserRole userRole = UserRole.builder()
                .userId(savedUser.getUserId())
                .roleId(sysRoleOtp.get().getRoleId())
                .build();
        userRoleRepo.save(userRole);


        return "Success";
    }

    @Override
    public String updateUser(UpdateUserRequest req) {
        return null;
    }

    @Override
    public String lockUser(String userId) {
        return null;
    }

    @Override
    public String resetPass(String userId) {
        return null;
    }
}
