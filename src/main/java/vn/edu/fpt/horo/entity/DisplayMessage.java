package vn.edu.fpt.horo.entity;

import lombok.*;
import vn.edu.fpt.horo.entity.common.Auditor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 **/

@Table(name = "display_messages")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DisplayMessage extends Auditor implements Serializable {
    @Id
    @Builder.Default
    private String displayMessageId = UUID.randomUUID().toString();
    private String code;
    @Builder.Default
    private String language = "en";
    private String message;

}
