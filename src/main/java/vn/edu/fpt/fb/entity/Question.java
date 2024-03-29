package vn.edu.fpt.fb.entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "QUESTION")
public class Question {

    @Id
    @Builder.Default
    @Column(name = "ID")
    private String questionId = UUID.randomUUID().toString();

    @Column(name = "QUESTION", nullable = false, columnDefinition = "longtext")
    private String question;

    @Column(name = "SUBJECT_ID", nullable = false, length = 50)
    private String subjectId;
}
