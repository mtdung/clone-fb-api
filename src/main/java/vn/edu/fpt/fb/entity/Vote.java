package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "VOTE")
public class Vote {
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String voteId = UUID.randomUUID().toString();

    @Column(name = "ANSWER_ID", nullable = false, length = 50)
    private String answerId;

    @Column(name = "USER_ID", length = 50)
    private String userId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
