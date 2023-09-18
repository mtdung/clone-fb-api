package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetListWithDrawResponse {
    String id;
    String bankCode;
    String userBankName;
    String userBankNumber;
    BigDecimal transAmount;
    LocalDateTime createDate;
    BigDecimal coinTransAmount;
    Boolean status;
    AccountResponse accountPayment;
}
