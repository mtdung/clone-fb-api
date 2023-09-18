package vn.edu.fpt.horo.dto.response.follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetFollowResponse {
    private String followerId;
    private AccountResponse followBy;
    private AccountResponse followTo;
}
