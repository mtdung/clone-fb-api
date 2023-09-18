package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "profiles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Profile extends Auditor {
    @Id
    @Builder.Default
    private String profileId = UUID.randomUUID().toString();
    private String gender;
    private LocalDateTime dateOfBirth;
    private String address;
    private String phoneNumber;
    @OneToOne
    @Lazy
    private _File avatar;
    private String description;
    @OneToOne
    @Lazy
    private _File horo;
}
