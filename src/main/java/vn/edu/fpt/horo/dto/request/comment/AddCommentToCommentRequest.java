package vn.edu.fpt.horo.dto.request.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.request.comment
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCommentToCommentRequest implements Serializable {

    private static final long serialVersionUID = 2833534825336710773L;
    private String content;
    private String imageAnswer;
}
