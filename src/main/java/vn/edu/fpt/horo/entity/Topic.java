package vn.edu.fpt.horo.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 19/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Topic extends Auditor {
    @Id
    @Builder.Default
    private String topicId = UUID.randomUUID().toString();
    private String topicName;
    @OneToOne
    @Lazy
    private _File imageTopic;
    private boolean isActive;
}
