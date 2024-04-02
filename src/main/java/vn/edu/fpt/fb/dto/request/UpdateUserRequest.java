package vn.edu.fpt.fb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequest {
    private String email;
    private String phoneNumber;
    private Integer status;
    private Integer userType;
}
