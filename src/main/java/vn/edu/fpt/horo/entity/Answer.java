package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.entity.custom
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "answers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Answer extends Auditor {
    @Id
    @Builder.Default
    private String answerId= UUID.randomUUID().toString();
    private String content;
    @Builder.Default
    private Integer liked = 0;
    @OneToOne
    @Lazy
    private _File imageAnswer;
    @ManyToMany
    @Lazy
    @ToString.Exclude
    private List<Account> likedUsers;
}
