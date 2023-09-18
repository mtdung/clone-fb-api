package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.CommentController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToCommentResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.CommentService;
import vn.edu.fpt.horo.service.PostService;

/**
 * vn.edu.fpt.accounts.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<AddCommentToCommentResponse>> addCommentToComment(String commentId, AddCommentToPostRequest request) {
        return responseFactory.response(commentService.addCommentToComment(commentId, request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> likedAnswer(String answerId) {
        commentService.likedAnswer(answerId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> likedComment(String commentId) {
        commentService.likedComment(commentId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateComment(String commentId, UpdateCommentRequest request) {
        commentService.updateComment(commentId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> deleteComment(String commentId, String postId) {
        commentService.deleteComment(commentId, postId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }
}
