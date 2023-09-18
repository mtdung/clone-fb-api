package vn.edu.fpt.horo.dto.response.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWithDrawResponse {
    String paymentTransId;
    BigDecimal withDrawAmount;
}
