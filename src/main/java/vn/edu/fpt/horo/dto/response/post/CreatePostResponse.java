package vn.edu.fpt.horo.dto.response.post;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.response.post
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class CreatePostResponse implements Serializable {

    private static final long serialVersionUID = -1857677822317944376L;
    private String postId;
}
