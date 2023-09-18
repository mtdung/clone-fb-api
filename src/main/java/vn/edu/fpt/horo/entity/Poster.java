package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
@Table(name = "posters")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Poster extends Auditor {

    @Id
    @Builder.Default
    private String postId = UUID.randomUUID().toString();
    private String title;
    @Builder.Default
    private Integer views = 0;
    @Builder.Default
    private Integer liked = 0;
    @OneToMany
    @Lazy
    @ToString.Exclude
    private List<Comment> comments;
    @ManyToMany
    @Lazy
    @ToString.Exclude
    private List<Account> likedUsers;
    @OneToOne
    @Lazy
    private _File imagePost;
}
