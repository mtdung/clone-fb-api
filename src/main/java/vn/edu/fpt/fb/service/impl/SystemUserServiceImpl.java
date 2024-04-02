package vn.edu.fpt.fb.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.common.constant.StatusConstant;
import vn.edu.fpt.fb.common.constant.StatusConstant.StatusUser;
import vn.edu.fpt.fb.common.constant.TypeConstant;
import vn.edu.fpt.fb.dto.request.CreateUserRequest;
import vn.edu.fpt.fb.dto.request.ResetPasswordRequest;
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

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

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
        log.info("Request CreateUser: {}", gson.toJson(req));
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
        //validate
        Optional<SysUser> sysUserOtp = sysUserRepo.findByEmailOrPhoneNumber(req.getEmail(), req.getPhoneNumber());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                EMAIL_PHONE_EXIST,
                sysUserOtp.isPresent());
        Optional<SysUser> sysUser = sysUserRepo.findById(req.getUserId());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        //update user
        SysUser sysUserUpdate = sysUser.get();
        log.info("SysUser update : {}", gson.toJson(sysUser));
        sysUserUpdate.setEmail(req.getEmail());
        sysUserUpdate.setPhoneNumber(req.getPhoneNumber());
        sysUserUpdate.setStatus(req.getStatus());
        sysUserRepo.save(sysUserUpdate);
        return "Success";
    }

    @Override
    public String lockUser(String userId) {
        Optional<SysUser> sysUser = sysUserRepo.findById(userId);
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        SysUser sysUserLock = sysUser.get();
        log.info("SysUser lock : {}", gson.toJson(sysUserLock));
        if(sysUserLock.getStatus().equals(StatusUser.STATUS_ACTIVE)){
            sysUserLock.setStatus(StatusUser.STATUS_LOCKED);
        }else if(sysUserLock.getStatus().equals(StatusUser.STATUS_LOCKED)){
            sysUserLock.setStatus(StatusUser.STATUS_ACTIVE);
        }else{
            sysUserLock.setStatus(StatusUser.STATUS_LOCKED);
        }
        sysUserRepo.save(sysUserLock);
        return "Success";
    }

    @Override
    public String resetPass(ResetPasswordRequest req) {
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                PASSWORD_INVALID,
                validatePassword(req.getNewPassword()));
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                REPEAT_PASSWORD_INVALID,
                req.getRepeatPassword()== null || !req.getRepeatPassword().equals(req.getNewPassword()));
        Optional<SysUser> sysUser = sysUserRepo.findById(req.getUserId());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        SysUser sysUserChangePass = sysUser.get();
        log.info("SysUser reset pass : {}", gson.toJson(sysUser));
        if(validatePassword(req.getNewPassword())){
            if(req.getRepeatPassword()!= null && req.getRepeatPassword().equals(req.getNewPassword())){
                sysUserChangePass.setPassword(req.getNewPassword());
                sysUserRepo.save(sysUserChangePass);
            }
        }
        sysUserRepo.save(sysUserChangePass);
        return "Success";
    }

    private boolean validatePassword(String password){
        // Kiểm tra độ dài
        if (password.length() < 8) {
            return false;
        }

        // Kiểm tra xem có ít nhất một chữ cái hay không
        if (!Pattern.compile("[a-zA-Z]").matcher(password).find()) {
            return false;
        }

        // Kiểm tra xem có ít nhất một chữ số hay không
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        // Kiểm tra xem có ít nhất một ký tự đặc biệt hay không
        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(password).find()) {
            return false;
        }
        // Mật khẩu đáp ứng tất cả các yêu cầu
        return true;
    }
}
