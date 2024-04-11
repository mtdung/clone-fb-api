package vn.edu.fpt.fb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RedisRequest implements Serializable {

    private String action;
    private String key;
    private String value;
}
