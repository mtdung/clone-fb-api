package vn.edu.fpt.horo.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.AdvisorTicketType;
import vn.edu.fpt.horo.constant.Gender;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "advisor_tickets")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AdvisorTicket extends Auditor {

    @Id
    @Builder.Default
    private String ticketId = UUID.randomUUID().toString();
    @ManyToOne
    @Lazy
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Advisor advisor;
    @Enumerated(EnumType.STRING)
    private AdvisorTicketType type;
    private String cardId;
    private String issuedPlace;
    private LocalDate issuedDate;
    private String issuedBy;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne
    private _File fontIdImage;
    @OneToOne
    private _File backIdImage;
    @OneToOne
    private _File portraitImage;
    private String summary;
    private String description;
    @Builder.Default
    @Column(name = "status")
    private Boolean ticketStatus = false;
    private String request;
    private String response;
}
