package vn.edu.fpt.fb.service.inter;

import vn.edu.fpt.fb.dto.request.CreateUserRequest;
import vn.edu.fpt.fb.dto.request.ChangePasswordRequest;
import vn.edu.fpt.fb.dto.request.UpdateUserRequest;

public interface SystemUserService {
    String createUser(CreateUserRequest req);
    String updateUser(UpdateUserRequest req);
    String lockUser(String userId);
    String changePassword(ChangePasswordRequest req);
    String resetPassword(String userId);
}
