package vn.edu.fpt.horo.dto.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.request.post
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePostRequest implements Serializable {

    private static final long serialVersionUID = 1130635315950896469L;
    private String title;
    private String imagePost;
}
