package vn.edu.fpt.horo.dto.response.post;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;
import vn.edu.fpt.horo.dto.response.comment.InformationCreatedByResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.response.post
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
public class GetPostDetailResponse {

    private static final long serialVersionUID = 7174465453876625263L;
    private String postId;
    private InformationCreatedByResponse createdBy;
    private FileResponse imagePost;
    private String title;
    private String problem;
    private String status;
    private Integer views;
    private Integer liked;
    private List<GetCommentResponse> comments;
    private List<AccountResponse> likedUsers;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class GetCommentResponse{
        private String commentId;
        private String content;
        private Integer liked = 0;
        private FileResponse imageComment;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
        private InformationCreatedByResponse createdBy;
    }
}
