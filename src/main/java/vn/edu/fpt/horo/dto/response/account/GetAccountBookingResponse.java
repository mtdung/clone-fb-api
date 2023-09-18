package vn.edu.fpt.horo.dto.response.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.request.profile.GetProfileBookingRespone;
import vn.edu.fpt.horo.entity.Coin;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.horo.dto.response.account
 *
 * @author : Portgas.D.Ace
 * @created : 31/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAccountBookingResponse implements Serializable {
    private static final long serialVersionUID = -7691671015423785656L;
    private String accountId;
    private String username;
    private String fullName;
    private GetProfileBookingRespone profile;
    private Coin coin;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
