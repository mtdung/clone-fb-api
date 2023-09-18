package vn.edu.fpt.horo.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.response.comment.InformationCreatedByResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity.Profile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.response.Post
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetPostResponse implements Serializable {

    private static final long serialVersionUID = -6413262838675150085L;
    private String postId;
    private InformationCreatedByResponse createdBy;
    private String title;
    private Integer views;
    private Integer liked;
    private FileResponse imagePost;
    private LocalDateTime createdDate;
    private List<AccountResponse> likedUsers;
    private Integer countComments;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class AccountResponse {
        private String accountId;
        private String fullName;
        private String username;
        private String email;
        private ProfileResponse profile;
    }

}
