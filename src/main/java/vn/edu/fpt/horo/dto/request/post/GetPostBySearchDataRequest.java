package vn.edu.fpt.horo.dto.request.post;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.PageableRequest;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.request.post
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class GetPostBySearchDataRequest extends PageableRequest implements Serializable {

    private static final long serialVersionUID = 6335283088048482210L;
    private String searchData;
}
