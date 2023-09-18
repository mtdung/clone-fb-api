package vn.edu.fpt.horo.entity;

import lombok.*;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "exchange_rates")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ExchangeRates extends Auditor {
    @Id
    @Builder.Default
    private String exchangeId = UUID.randomUUID().toString();
    private BigDecimal coefficients;
    private BigDecimal perUnit;
    private String bankCode;
    private Boolean status;
}
