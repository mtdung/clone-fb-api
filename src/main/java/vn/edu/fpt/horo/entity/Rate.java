package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "rates")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Rate extends Auditor {
    @Id
    @Builder.Default
    private String rateId = UUID.randomUUID().toString();
    private Float point;
    @ManyToOne
    @Lazy
    private Account rateBy;
    private String content;
}
