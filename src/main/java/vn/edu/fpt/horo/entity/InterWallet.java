package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.InterWalletStatus;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.entity
 *
 * @author : Portgas.D.Ace
 * @created : 04/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "inter_wallets")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class InterWallet extends Auditor {
    @Id
    @Builder.Default
    private String walletId = UUID.randomUUID().toString();
    private String transactionId;
    @ManyToOne
    @Lazy
    private Account fromAccount;
    @ManyToOne
    @Lazy
    private Account toAccount;
    private BigDecimal amount;
    private LocalDateTime finishDate;
    @Enumerated(EnumType.STRING)
    private InterWalletStatus status;
}
