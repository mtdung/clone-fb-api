package vn.edu.fpt.horo.entity;

import lombok.*;
import vn.edu.fpt.horo.constant.ConditionOperator;
import vn.edu.fpt.horo.constant.ConditionType;
import vn.edu.fpt.horo.entity.common.Auditor;
import vn.edu.fpt.horo.entity.converter.StringListConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "promotion_conditions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PromotionCondition extends Auditor {
    @Id
    @Builder.Default
    private String conditionId = UUID.randomUUID().toString();
    @Enumerated
    private ConditionType type;
    @Enumerated
    private ConditionOperator operator;
    @Convert(converter = StringListConverter.class)
    @Column(name = "condition_values")
    private List<String> values;
}
