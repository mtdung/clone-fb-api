package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToCommentResponse;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToPostResponse;

/**
 * vn.edu.fpt.accounts.service
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface CommentService {


    AddCommentToPostResponse addCommentToPost(String postId, AddCommentToPostRequest request);

    void deleteComment(String commentId, String postId);

    void updateComment(String commentId, UpdateCommentRequest request);

    AddCommentToCommentResponse addCommentToComment(String commentId, AddCommentToPostRequest request);


    void likedComment(String commentId);

    void likedAnswer(String answerId);
}
