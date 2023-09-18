package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.constant.Gender;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "advisors")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Advisor extends Auditor implements Serializable {
    @Id
    @Builder.Default
    private String advisorId = UUID.randomUUID().toString();
    @OneToOne
    @Lazy
    private Account account;
    private String cardId;
    private String issuedPlace;
    private LocalDate issuedDate;
    private String issuedBy;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne
    @Lazy
    private _File fontIdImage;
    @Lazy
    @OneToOne
    private _File backIdImage;
    @Lazy
    @OneToOne
    private _File portraitImage;
    private String summary;
    private String description;
    @ManyToMany
    @Lazy
    @ToString.Exclude
    private List<Topic> topics = new ArrayList<>();
    private BigDecimal avgPrice;
    @Builder.Default
    private Float totalRate = 0F;
    @OneToMany
    @Lazy
    @ToString.Exclude
    private List<_PackageService> packageServices = new ArrayList<>();
    @OneToMany
    @Lazy
    @ToString.Exclude
    private List<Rate> rates = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AdvisorStatus status = AdvisorStatus.INACTIVE;
    @ManyToMany
    @Lazy
    private List<Account> followers;
    private Integer experience;
}
