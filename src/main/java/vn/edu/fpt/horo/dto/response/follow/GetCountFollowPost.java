package vn.edu.fpt.horo.dto.response.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetCountFollowPost {
    int countFollower;
    int countFollowTo;
    int countPost;
}
