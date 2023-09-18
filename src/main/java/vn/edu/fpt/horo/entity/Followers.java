package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "followers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Followers implements Serializable {

    private static final long serialVersionUID = -1536294940644844647L;
    @Id
    @Builder.Default
    private String followerId = UUID.randomUUID().toString();
    @ManyToOne
    @Lazy(value = true)
    private Account followBy;
    @ManyToOne
    @Lazy(value = true)
    private Account followTo;

    private Boolean isFollow;

}
