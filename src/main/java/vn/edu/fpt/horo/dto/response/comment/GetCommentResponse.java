package vn.edu.fpt.horo.dto.response.comment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.common.AuditableResponse;

/**
 * vn.edu.fpt.accounts.dto.response.comment
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
@JsonPropertyOrder(value = {"commentId", "content", "liked"})
public class GetCommentResponse extends AuditableResponse {

    private static final long serialVersionUID = 777097239889084764L;
    private String commentId;
    private String content;
    private Integer liked;
}
