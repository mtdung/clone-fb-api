package vn.edu.fpt.horo.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Auditable;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.entity
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Entity
@Table(name = "policy")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Policy extends Auditor {
    private static final long serialVersionUID = 4660453419933524510L;
    @Id
    @Builder.Default
    private String policyId = UUID.randomUUID().toString();
    private String policyName;
    @OneToOne
    @Lazy
    private _File filePolicy;

}
