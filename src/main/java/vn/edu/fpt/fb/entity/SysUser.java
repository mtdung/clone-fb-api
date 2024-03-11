package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 12/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "SYS_USER")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SysUser implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String userId = UUID.randomUUID().toString();
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PASS_WORD")
    private String password;
}
