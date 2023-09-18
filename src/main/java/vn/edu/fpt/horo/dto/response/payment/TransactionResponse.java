package vn.edu.fpt.horo.dto.response.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.ExchangeRates;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String id = UUID.randomUUID().toString();

    private String errorCode;// 00 là thành công còn lại là thất bại

    private String errorMessage;// nội dung kết quả

    private LocalDateTime createDate;// thời gian nạp hoặc yêu cầu rút

    private LocalDateTime endDate;// thời gian hoàn thành nạp/rút

    private Boolean status; // true là đã nạp/rút thành công, false là thất bại, null là chưa xử lý

    private BigDecimal transAmount;// gia tri chuyen doi

    private BigDecimal coinTransAmount;// so coin chuyen doi

    private String bankCode;// ngân hàng nạp hoặc rút

    private String userBankNumber;// STK người rút

    private String userBankName;// Tên TKNH người rút

    private Long transType;//0 la nap tien, 1 la rut tien
}
