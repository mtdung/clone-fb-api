package vn.edu.fpt.horo.dto.admin;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestListAdvisorResponse {
    String ticketId;
    String advisorId;
    String accountName;
    Boolean status;
    String type;
    LocalDateTime createDate;
}
