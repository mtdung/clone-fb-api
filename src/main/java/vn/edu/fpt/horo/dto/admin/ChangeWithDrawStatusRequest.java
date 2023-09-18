package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChangeWithDrawStatusRequest {
    String errorCode;
    String errorMessage;
}
