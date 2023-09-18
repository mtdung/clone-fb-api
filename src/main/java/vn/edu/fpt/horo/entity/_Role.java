package vn.edu.fpt.horo.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 **/

@Table(name = "roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class _Role extends Auditor {
    private static final long serialVersionUID = -2614725154048384263L;
    @Id
    @Builder.Default
    private String roleId = UUID.randomUUID().toString();
    private String roleName;
    private String description;
    @Builder.Default
    private Boolean isEnable = true;

}
