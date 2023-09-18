package vn.edu.fpt.horo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "payment_trans_detail")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentTransDetail {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private Account accountPayment;// tài khoản nạp hoặc rút

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

    @ManyToOne
    private ExchangeRates rates;// tỉ lệ qui đổi được áp dụng

    private BigDecimal adminCollectAmount;

    private Long transType;//0 la nap tien, 1 la rut tien
}
