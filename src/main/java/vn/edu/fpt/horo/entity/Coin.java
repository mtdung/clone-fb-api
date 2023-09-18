package vn.edu.fpt.horo.entity;

import lombok.*;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "coins")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Coin extends Auditor {
    private static final long serialVersionUID = 1119730021899546000L;
    @Id
    @Builder.Default
    private String coinId = UUID.randomUUID().toString();

    @Column(name = "amount")
    private BigDecimal amount;

}
