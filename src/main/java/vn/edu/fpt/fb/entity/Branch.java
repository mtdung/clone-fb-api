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
@Table(name = "BRANCH")
public class Branch {

    @Id
    @Builder.Default
    @Column(name = "ID")
    private String branchId = UUID.randomUUID().toString();

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;
}