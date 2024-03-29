package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

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
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "USER_TYPE")
    private Integer userType;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
