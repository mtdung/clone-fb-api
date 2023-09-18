package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.entity
 *
 * @author : Portgas.D.Ace
 * @created : 30/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "reports")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Report extends Auditor {
    @Id
    @Builder.Default
    private String reportId = UUID.randomUUID().toString();
    @ManyToOne
    @Lazy
    private Advisor reportTo;
    private String content;
    @OneToOne
    @Lazy
    private _File imageReport;
    private Boolean status;

}
