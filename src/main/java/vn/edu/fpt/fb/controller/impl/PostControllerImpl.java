package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.fb.dto.request.post.CreatePostRequest;
import vn.edu.fpt.fb.dto.request.post.UpdatePostRequest;
import vn.edu.fpt.fb.dto.response.comment.AddCommentToPostResponse;
import vn.edu.fpt.fb.dto.response.post.CreatePostResponse;
import vn.edu.fpt.fb.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.fb.dto.response.post.GetPostResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

import java.util.List;


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
public class PostControllerImpl implements PostController {

    private final PostService postService;
    private final ResponseFactory responseFactory;
    private final CommentService commentService;

    @Override
    public ResponseEntity<GeneralResponse<CreatePostResponse>> createPost(CreatePostRequest request) {
        return responseFactory.response(postService.createPost(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<AddCommentToPostResponse>> addCommentToPost(String postId, AddCommentToPostRequest request) {
        return responseFactory.response(commentService.addCommentToPost(postId, request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updatePost(String postId, UpdatePostRequest request) {
        postService.updatePost(postId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> likedPost(String postId) {
        postService.likedPost(postId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetPostResponse>>> getPost(Integer page, Integer size, String sort) {
        return responseFactory.response(postService.getPost(page, size, sort));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetPostDetailResponse>>> getPostAccountId(String accountId, Pageable pageable) {
        return responseFactory.response(postService.getPostByAccountId(accountId, pageable));
    }


    @Override
    public ResponseEntity<GeneralResponse<GetPostDetailResponse>> getPostDetail(String postId) {
        return responseFactory.response(postService.getPostDetail(postId));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> deletePost(String postId) {
        postService.deletePost(postId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<List<GetPostDetailResponse>>> searchPostByName(String postName) {
        return responseFactory.response(postService.searchPostByName(postName));
    }


}
