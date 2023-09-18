package vn.edu.fpt.horo.dto.request.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.common.CreateFileRequest;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.request.profile
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChangeAvatarRequest implements Serializable {

    private static final long serialVersionUID = 3747410200687205822L;
    private CreateFileRequest avatar;
}
