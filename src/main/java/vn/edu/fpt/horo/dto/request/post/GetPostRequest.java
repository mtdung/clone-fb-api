package vn.edu.fpt.horo.dto.request.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.AuditableRequest;
import vn.edu.fpt.horo.utils.RequestDataUtils;

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
@ToString
@SuperBuilder
public class GetPostRequest extends AuditableRequest implements Serializable {

    private static final long serialVersionUID = -997259352820848426L;
    private String postId;
    private String title;
    private String problem;
    private String status;

    public String getTitle() {
        return RequestDataUtils.convertSearchableData(title);
    }
    public String getProblem() {
        return RequestDataUtils.convertSearchableData(problem);
    }

    public String getStatus() {
        return RequestDataUtils.convertSearchableData(status);
    }
}
