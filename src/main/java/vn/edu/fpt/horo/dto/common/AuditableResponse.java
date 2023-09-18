package vn.edu.fpt.horo.dto.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.cache.UserInfo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.common
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@JsonPropertyOrder({"createdBy", "createdByInfo", "createdDate", "lastModifiedBy", "lastModifiedByInfo", "lastModifiedDate"})
public abstract class AuditableResponse implements Serializable {

    private static final long serialVersionUID = -8406677523279755332L;
    protected UserInfo createdBy;
    protected LocalDateTime createdDate;
    protected UserInfo lastModifiedBy;
    protected LocalDateTime lastModifiedDate;
}
