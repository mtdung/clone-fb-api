package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.PromotionStatus;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "promotions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Promotion extends Auditor {
    private static final long serialVersionUID = -7148597583922208863L;
    @Id
    @Builder.Default
    private String promotionId = UUID.randomUUID().toString();
    private String promotionName;
    @OneToMany
    @Lazy
    @ToString.Exclude
    private List<PromotionCondition> conditions;
    private BigDecimal discount;
    private BigDecimal maxDiscount;
    private BigDecimal budget;
    @Enumerated
    private PromotionStatus status;
}
