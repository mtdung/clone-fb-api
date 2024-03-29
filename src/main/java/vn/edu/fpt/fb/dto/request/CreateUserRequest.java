package vn.edu.fpt.fb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserRequest {
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private Integer status;
    private Integer userType;
    private String roleId;
}
