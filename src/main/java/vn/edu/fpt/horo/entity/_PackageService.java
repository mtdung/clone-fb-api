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
@Table(name = "package_services")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class _PackageService extends Auditor {
    @Id
    @Builder.Default
    private String packageServiceId = UUID.randomUUID().toString();
    private String serviceName;
    private String description;
    @ManyToOne
    @Lazy
    private Topic topic;
    private BigDecimal price;
    private Boolean isActive;
    private Integer slot;
    @ManyToOne
    @Lazy
    private Advisor advisor;
}
