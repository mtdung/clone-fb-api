package vn.edu.fpt.fb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.common.constant.StatusConstant.StatusUser;
import vn.edu.fpt.fb.common.constant.TypeConstant;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;
import vn.edu.fpt.fb.entity.Profile;
import vn.edu.fpt.fb.entity.SysRole;
import vn.edu.fpt.fb.entity.SysUser;
import vn.edu.fpt.fb.entity.UserRole;
import vn.edu.fpt.fb.repository.ProfileRepo;
import vn.edu.fpt.fb.repository.SysRoleRepo;
import vn.edu.fpt.fb.repository.SysUserRepo;
import vn.edu.fpt.fb.repository.UserRoleRepo;
import vn.edu.fpt.fb.service.inter.AuthenticationService;
import vn.edu.fpt.fb.service.inter.HandleTokenService;
import vn.edu.fpt.fb.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static vn.edu.fpt.fb.common.constant.AccountConstant.*;
import static vn.edu.fpt.fb.common.constant.Constant.USERNAME_OR_PASS_EMPTY;
import static vn.edu.fpt.fb.common.constant.Constant.WRONG_USERNAME_PASSWORD;
import static vn.edu.fpt.fb.utils.RequestDataUtils.isNullOrEmptyString;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initUser() {
        Optional<SysUser> sysUserOtp = sysUserRepo.findByEmailOrUsernameOrPhoneNumber(ADMIN_EMAIL,
                ADMIN_USERNAME,
                ADMIN_PHONE);
        if(sysUserOtp.isEmpty()){
            SysUser sysUser = SysUser.builder()
                    .username(ADMIN_USERNAME)
                    .email(ADMIN_EMAIL)
                    .phoneNumber(ADMIN_PHONE)
                    .password(encoder.encode(ADMIN_PASSWORD))
                    .status(StatusUser.STATUS_ACTIVE)
                    .userType(TypeConstant.UserType.TYPE_NORMAL)
                    .build();

            SysUser savedUser = sysUserRepo.save(sysUser);
            Profile profile = Profile.builder()
                    .fullName(ADMIN_USERNAME)
                    .userId(savedUser.getUserId())
                    .nickName(ADMIN_USERNAME)
                    .build();
            profileRepo.save(profile);
            Optional<SysRole> sysRoleOtp = sysRoleRepo.findFirstByRoleName(ROLE_ADMIN);
            if(sysRoleOtp.isPresent()){
                UserRole userRole = UserRole.builder()
                        .userId(savedUser.getUserId())
                        .roleId(sysRoleOtp.get().getRoleId())
                        .build();
                userRoleRepo.save(userRole);
            }

        }
    }

    @Override
    public void initRole() {
        List<SysRole> roles = new ArrayList<>();
        DEFAULT_ROLE.forEach(role -> {
            Optional<SysRole> sysRoleOtp = sysRoleRepo.findFirstByRoleName(role);
            if(sysRoleOtp.isEmpty()){
                SysRole userRole = SysRole.builder()
                        .roleName(role)
                        .isEnabled(true)
                        .build();
                roles.add(userRole);
            }
        });
        if(!roles.isEmpty()) {
            sysRoleRepo.saveAll(roles);
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                USERNAME_OR_PASS_EMPTY,
                isNullOrEmptyString(request.getEmailOrUsernameOrPhoneNumber(), request.getPassword()));
        Optional<SysUser> sysUserOtp = sysUserRepo.findByEmailOrUsernameOrPhoneNumber(request.getEmailOrUsernameOrPhoneNumber(),
                request.getEmailOrUsernameOrPhoneNumber(),
                request.getEmailOrUsernameOrPhoneNumber());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                WRONG_USERNAME_PASSWORD,
                sysUserOtp.isEmpty());
        SysUser sysUser = sysUserOtp.get();
        List<SysRole> roles = sysRoleRepo.getAllRoleByUserId(sysUser.getUserId());
        Profile profile = profileRepo.findProfileByUserId(sysUser.getUserId());
        String token = tokenService.generateToken(sysUser, roles, getUserByUsername(sysUser, roles));
        String refreshToken = tokenService.generateRefreshToken(request);
        return LoginResponse.builder()
                .accountId(sysUser.getUserId())
                .username(sysUser.getUsername())
                .fullName(profile.getFullName())
                .email(sysUser.getEmail())
                .token(token)
                .tokenExpireTime(tokenService.getExpiredTimeFromToken(token))
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(tokenService.getExpiredTimeFromToken(refreshToken))
                .build();
    }

    public UserDetails getUserByUsername(SysUser sysUser, List<SysRole> roles) {
        return User.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .authorities(this.getAuthorities(roles).toArray(String[]::new))
                .build();
    }

    private List<String> getAuthorities(List<SysRole> roles) {
        List<SysRole> roleEnable = roles.stream()
                .filter(SysRole::getIsEnabled).collect(Collectors.toList());

        return roleEnable.stream()
                .map(role -> "ROLE_" + role.getRoleName()).collect(Collectors.toList());
    }
}
