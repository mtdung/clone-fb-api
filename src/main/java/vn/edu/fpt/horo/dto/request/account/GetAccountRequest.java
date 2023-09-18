package vn.edu.fpt.horo.dto.request.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.PageableRequest;
import vn.edu.fpt.horo.utils.RequestDataUtils;

import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.request.account
 **/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@SuperBuilder
public class GetAccountRequest extends PageableRequest {

    private static final long serialVersionUID = 6388530270206097979L;
    private String accountId;
    private String username;
    private String fullName;
    private String email;
    private Boolean isEnable;
    private Boolean isCredentialNonExpired;
    private Boolean isNonExpired;
    private Boolean isNonLocked;
    private String createdDateFrom;
    private String createdDateTo;
    private String lastModifiedDateFrom;
    private String lastModifiedDateTo;

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return RequestDataUtils.convertSearchableData(username);
    }

    public String getFullName() {
        return RequestDataUtils.convertSearchableData(fullName);
    }

    public String getEmail() {
        return RequestDataUtils.convertSearchableData(email);
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public Boolean getCredentialNonExpired() {
        return isCredentialNonExpired;
    }

    public Boolean getNonExpired() {
        return isNonExpired;
    }

    public Boolean getNonLocked() {
        return isNonLocked;
    }

    public LocalDateTime getCreatedDateFrom() {
        return RequestDataUtils.convertDateFrom(createdDateFrom);
    }

    public LocalDateTime getCreatedDateTo() {
        return RequestDataUtils.convertDateTo(createdDateTo);
    }

    public LocalDateTime getLastModifiedDateFrom() {
        return RequestDataUtils.convertDateFrom(lastModifiedDateFrom);
    }

    public LocalDateTime getLastModifiedDateTo() {
        return RequestDataUtils.convertDateTo(lastModifiedDateTo);
    }
}
