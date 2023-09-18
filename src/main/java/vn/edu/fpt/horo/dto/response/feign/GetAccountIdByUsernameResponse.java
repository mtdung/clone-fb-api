package vn.edu.fpt.horo.dto.response.feign;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.response.feign
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAccountIdByUsernameResponse implements Serializable {

    private static final long serialVersionUID = 5166337769648000898L;
    @JsonIgnore
    private List<String> accountIds;
}
