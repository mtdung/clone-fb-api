package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 **/

@Table(name = "accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Account implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    private String accountId = UUID.randomUUID().toString();
    private String email;
    private String username;
    private String fullName;
    private String password;
    @ManyToMany
    @Lazy
    @ToString.Exclude
    private List<_Role> roles;
    @OneToOne
    @Lazy
    private Profile profile;
    @OneToOne
    @Lazy
    private Coin coin;
    @Builder.Default
    private Boolean isNonExpired = true;
    @Builder.Default
    private Boolean isNonLocked = true;
    @Builder.Default
    private Boolean isCredentialNonExpired = true;
    @Builder.Default
    private Boolean isEnabled = true;
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @ManyToMany
    @Lazy
    private List<Advisor> following = new ArrayList<>();
    private Boolean isOnline;
    private Boolean verified;
}
