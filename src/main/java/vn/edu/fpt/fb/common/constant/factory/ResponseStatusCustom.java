package vn.edu.fpt.fb.common.constant.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;

import java.io.Serializable;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatusCustom implements Serializable {

    private static final long serialVersionUID = -8476144773001611196L;
    private Integer status;
    private String code;
    private String message;
    @Builder.Default
    private LocalDateTime responseDate = LocalDateTime.now();
    public ResponseStatusCustom(Integer status, String code){
        this.status = status;
        this.code = code;
        this.message = null;
        this.responseDate = LocalDateTime.now(LocaleContextHolder.getTimeZone().toZoneId());
    }

    public ResponseStatusCustom(Integer status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
        this.responseDate = LocalDateTime.now(LocaleContextHolder.getTimeZone().toZoneId());
    }

    public ResponseStatusCustom(ResponseStatusEnum statusEnum){
        this.status = statusEnum.getStatus();
        this.code = statusEnum.getCode();
        this.message = null;
        this.responseDate = LocalDateTime.now(LocaleContextHolder.getTimeZone().toZoneId());
    }

}
