package vn.edu.fpt.fb.entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "ANSWER")
public class Answer {

    @Id
    @Builder.Default
    @Column(name = "ID")
    private String answerId = UUID.randomUUID().toString();

    @Column(name = "NAME", nullable = false, columnDefinition = "longtext")
    private String name;

    @Column(name = "QUESTION_ID", nullable = false, length = 50)
    private String questionId;

    @Column(name = "IS_CORRECT", nullable = false)
    private boolean isCorrect;
}