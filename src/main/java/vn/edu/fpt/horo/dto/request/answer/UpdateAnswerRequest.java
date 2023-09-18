package vn.edu.fpt.horo.dto.request.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.request.answer
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAnswerRequest implements Serializable {
    private static final long serialVersionUID = 8226644414495495773L;
    private String content;
    private String imageAnswer;
}
