package vn.edu.fpt.horo.dto.request.payment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeDeserializer;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IncreaseCoinRequest {
    BigDecimal transAmount;
    String errorCode;
    String errorMessage;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    LocalDateTime issueDate;
    String bankCode;
    Boolean status;
}
