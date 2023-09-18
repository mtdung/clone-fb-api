package vn.edu.fpt.horo.dto.request.payment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeDeserializer;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWithDrawRequest {
    BigDecimal coinWithDraw;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    LocalDateTime createDate;
    String bankCode;
    String userBankNumber;
    String userBankName;
}
