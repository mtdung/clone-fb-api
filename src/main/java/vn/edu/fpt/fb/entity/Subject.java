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
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @Builder.Default
    @Column(name = "ID")
    private String subjectId = UUID.randomUUID().toString();

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "BRANCH_ID", nullable = false, length = 50)
    private String branchId;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}