package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentOutCompilationResponse {
    private String id;

    private String bankCode;// ngân hàng nạp hoặc rút

    private String fullName;// tài khoản nạp hoặc rút

    private String username;// tài khoản nạp hoặc rút

    private String accountId;

    private LocalDateTime createDate;// thời gian nạp hoặc yêu cầu rút

    private Boolean status; // true là đã nạp/rút thành công, false là thất bại, null là chưa xử lý

    private BigDecimal transAmount;// gia tri chuyen doi

    private BigDecimal coinTransAmount;// so coin chuyen doi

    private String userBankNumber;// STK người rút
}
