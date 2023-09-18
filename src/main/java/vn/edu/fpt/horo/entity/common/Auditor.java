package vn.edu.fpt.horo.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.edu.fpt.horo.entity.Account;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.entity.common
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditor {
    @CreatedBy
    @ManyToOne
    @Lazy
    private Account createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    @ManyToOne
    @Lazy
    private Account lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @ManyToOne
    @Lazy
    private Account deletedBy;
    private LocalDateTime deletedDate;
}
