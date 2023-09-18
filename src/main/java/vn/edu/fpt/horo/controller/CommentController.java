package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToCommentResponse;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToPostResponse;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RequestMapping("${app.application-context}/public/api/v1/comments")
public interface CommentController {
    @PostMapping("/{comment-id}/comment")
    ResponseEntity<GeneralResponse<AddCommentToCommentResponse>> addCommentToComment(@PathVariable(name = "comment-id") String commentId, @RequestBody AddCommentToPostRequest request);

    @PostMapping("/{answer-id}/liked")
    ResponseEntity<GeneralResponse<Object>> likedAnswer(@PathVariable(name = "answer-id") String answerId);

    @PostMapping("/{comment-id}/liked")
    ResponseEntity<GeneralResponse<Object>> likedComment(@PathVariable(name = "comment-id") String commentId);

    @PutMapping("/{comment-id}")
    ResponseEntity<GeneralResponse<Object>> updateComment(@PathVariable(name = "comment-id") String commentId, @RequestBody UpdateCommentRequest request);

    @DeleteMapping("/{comment-id}/{post-id}")
    ResponseEntity<GeneralResponse<Object>> deleteComment(@PathVariable(name = "comment-id") String commentId, @PathVariable(name = "post-id") String postId);

}
