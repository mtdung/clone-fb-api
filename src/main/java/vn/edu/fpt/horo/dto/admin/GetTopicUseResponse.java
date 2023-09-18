package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetTopicUseResponse {
    String topicId;
    String topicName;
    Integer numberUseTopic;
    boolean isActive;
}
