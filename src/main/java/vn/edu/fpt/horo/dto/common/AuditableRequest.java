package vn.edu.fpt.horo.dto.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.utils.RequestDataUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.common
 **/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public abstract class AuditableRequest extends PageableRequest implements Serializable {

    private static final long serialVersionUID = -7225952892240337194L;
    protected String createdBy;
    protected String createdDateFrom;
    protected String createdDateTo;
    protected String lastModifiedBy;
    protected String lastModifiedDateFrom;
    protected String lastModifiedDateTo;

    public String getCreatedBy() {
        return RequestDataUtils.convertSearchableData(createdBy);
    }

    public LocalDateTime getCreatedDateFrom() {
        return RequestDataUtils.convertDateFrom(createdDateFrom);
    }

    public LocalDateTime getCreatedDateTo() {
        return RequestDataUtils.convertDateTo(createdDateTo);
    }

    public String getLastModifiedBy() {
        return RequestDataUtils.convertSearchableData(lastModifiedBy);
    }

    public LocalDateTime getLastModifiedDateFrom() {
        return RequestDataUtils.convertDateFrom(lastModifiedDateFrom);
    }

    public LocalDateTime getLastModifiedDateTo() {
        return RequestDataUtils.convertDateTo(lastModifiedDateTo);
    }
}
