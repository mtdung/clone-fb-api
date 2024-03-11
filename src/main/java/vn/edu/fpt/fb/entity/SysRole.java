package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 12/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "SYS_ROLE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SysRole implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String roleId = UUID.randomUUID().toString();
    @Column(name = "ROLE_NAME")
    private String roleName;
}
