package vn.edu.fpt.horo.dto.response.advisor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdvisorFreeTimeResponse {

    private Map<Integer, Boolean> slots;
}
