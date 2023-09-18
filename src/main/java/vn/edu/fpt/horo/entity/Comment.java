package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "comments")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Comment extends Auditor {
    @Id
    @Builder.Default
    private String commentId= UUID.randomUUID().toString();
    private String content;
    @Builder.Default
    private Integer liked = 0;
    @ManyToMany
    @Lazy
    @ToString.Exclude
    private List<Account> likedUsers;
    @OneToOne
    @Lazy
    private _File imageComment;
    @OneToMany
    @Lazy
    @ToString.Exclude
    private List<Answer> answers;

}
