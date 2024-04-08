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
import vn.edu.fpt.fb.dto.request.ChangePasswordRequest;
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
import vn.edu.fpt.fb.utils.Utils;

import java.util.Optional;
import java.util.Random;
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

        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                PHONE_NUMBER_INVALID,
                !validatePhoneNumber(req.getPhoneNumber()));

        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                EMAIL_INVALID,
                !validateEmail(req.getEmail()));
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


        return SUCCESS;
    }

    @Override
    public String updateUser(UpdateUserRequest req) {
        log.info("Request UpdateUser : {}", gson.toJson(req));
        //validate
        Optional<SysUser> sysUserOtp = sysUserRepo.findByEmailOrPhoneNumber(req.getEmail(), req.getPhoneNumber());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                EMAIL_PHONE_EXIST,
                sysUserOtp.isPresent());

        Optional<SysUser> sysUser = sysUserRepo.findById(req.getUserId());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());

        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                PHONE_NUMBER_INVALID,
                !validatePhoneNumber(req.getPhoneNumber()));

        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                EMAIL_INVALID,
                !validateEmail(req.getEmail()));
        //update user
        SysUser sysUserUpdate = sysUser.get();
        sysUserUpdate.setEmail(req.getEmail());
        sysUserUpdate.setPhoneNumber(req.getPhoneNumber());
        sysUserUpdate.setStatus(req.getStatus());
        sysUserRepo.save(sysUserUpdate);
        return SUCCESS;
    }

    @Override
    public String lockUser(String userId) {
        log.info("Request LockUser : {}", userId);
        Optional<SysUser> sysUser = sysUserRepo.findById(userId);
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        SysUser sysUserLock = sysUser.get();
        if(sysUserLock.getStatus().equals(StatusUser.STATUS_ACTIVE)){
            sysUserLock.setStatus(StatusUser.STATUS_LOCKED);
        }else if(sysUserLock.getStatus().equals(StatusUser.STATUS_LOCKED)){
            sysUserLock.setStatus(StatusUser.STATUS_ACTIVE);
        }else{
            return STATUS_INVALID_LOCK;
        }
        sysUserRepo.save(sysUserLock);
        return SUCCESS;
    }

    @Override
    public String changePassword(ChangePasswordRequest req) {
        log.info("Request ChangePassword : {}", gson.toJson(req));
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                PASSWORD_INVALID,
                !validatePassword(req.getNewPassword()));
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                REPEAT_PASSWORD_INVALID,
                req.getRepeatPassword()== null || !req.getRepeatPassword().equals(req.getNewPassword()));
        Optional<SysUser> sysUser = sysUserRepo.findById(req.getUserId());
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        SysUser sysUserChangePass = sysUser.get();
        if(validatePassword(req.getNewPassword())){
            if(req.getRepeatPassword()!= null && req.getRepeatPassword().equals(req.getNewPassword())){
                sysUserChangePass.setPassword(req.getNewPassword());
                sysUserRepo.save(sysUserChangePass);
            }
        }
        sysUserRepo.save(sysUserChangePass);
        return SUCCESS;
    }

    @Override
    public String resetPassword(String userId) {
        log.info("Request ResetPassword : {}", userId);
        Optional<SysUser> sysUser = sysUserRepo.findById(userId);
        Utils.throwException(ResponseStatusEnum.BAD_REQUEST,
                ACCOUNT_NOT_EXIST,
                sysUser.isEmpty());
        SysUser sysUserResetPass = sysUser.get();
        sysUserResetPass.setPassword(randomPassword());
        sysUserRepo.save(sysUserResetPass);
        return SUCCESS;
    }

    private String randomPassword(){
        //random 1 pass gồm 8 kí tự ngẫu nhiên
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*!#@";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
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

    private boolean validatePhoneNumber(String phoneNumber){
        //check số đth gồm 11 số
        String regex = "^\\d{11}$";
        if (Pattern.matches(regex, phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (Pattern.matches(regex, email)) {
            return true;
        } else {
            return false;
        }
    }

}
