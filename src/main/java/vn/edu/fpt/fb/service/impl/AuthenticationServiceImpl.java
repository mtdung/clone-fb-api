package vn.edu.fpt.fb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;
import vn.edu.fpt.fb.entity.Profile;
import vn.edu.fpt.fb.entity.SysRole;
import vn.edu.fpt.fb.entity.SysUser;
import vn.edu.fpt.fb.repository.ProfileRepo;
import vn.edu.fpt.fb.repository.SysRoleRepo;
import vn.edu.fpt.fb.repository.SysUserRepo;
import vn.edu.fpt.fb.service.inter.AuthenticationService;
import vn.edu.fpt.fb.service.inter.HandleTokenService;
import vn.edu.fpt.fb.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    ProfileRepo profileRepo;

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
                .map(SysRole::getRoleName).collect(Collectors.toList());
    }
}
