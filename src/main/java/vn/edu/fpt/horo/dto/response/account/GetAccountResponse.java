package vn.edu.fpt.horo.dto.response.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.entity._Role;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.response.account
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAccountResponse implements Serializable {

    private static final long serialVersionUID = -7918752112103180312L;
    private String accountId;
    private String username;
    private String fullName;
    private String email;
    private ProfileResponse profile;
    @JsonIgnore
    private List<_Role> roles;
    private Boolean isEnable;
    private Boolean isNonExpired;
    private Boolean isNonLocked;
    private Boolean isCredentialNonExpired;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private LocalDateTime createdDate;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private LocalDateTime lastModifiedDate;

}
