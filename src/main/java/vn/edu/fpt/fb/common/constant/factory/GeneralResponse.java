package vn.edu.fpt.fb.common.constant.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> implements Serializable {

    private static final long serialVersionUID = -369541457518166195L;
    private ResponseStatusCustom status;
    private T data;
}
