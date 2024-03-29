package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


@Table(name = "USER_ROLE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserRole implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String userRoleId = UUID.randomUUID().toString();
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "ROLE_ID")
    private String roleId;
}
