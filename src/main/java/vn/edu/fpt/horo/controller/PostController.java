package vn.edu.fpt.horo.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.dto.request.post.UpdatePostRequest;
import vn.edu.fpt.horo.dto.response.account.InformationAdvisorFollowing;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToPostResponse;
import vn.edu.fpt.horo.dto.response.post.CreatePostResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostResponse;

import java.util.List;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RequestMapping("${app.application-context}/public/api/v1/posts")
public interface PostController {

    @PostMapping
    ResponseEntity<GeneralResponse<CreatePostResponse>> createPost(@RequestBody CreatePostRequest request);

    @PostMapping("/{post-id}/comment")
    ResponseEntity<GeneralResponse<AddCommentToPostResponse>> addCommentToPost(@PathVariable(name = "post-id") String postId, @RequestBody AddCommentToPostRequest request);

    @PutMapping("/{post-id}")
    ResponseEntity<GeneralResponse<Object>> updatePost(@PathVariable(name = "post-id") String postId, @RequestBody UpdatePostRequest request);

    @PostMapping("/{post-id}/liked")
    ResponseEntity<GeneralResponse<Object>> likedPost(@PathVariable(name = "post-id") String postId);

    @GetMapping
    ResponseEntity<GeneralResponse<Page<GetPostResponse>>> getPost(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort", required = false) String sort

    );

    @GetMapping("/accounts/{account-id}")
    ResponseEntity<GeneralResponse<Page<GetPostDetailResponse>>> getPostAccountId(@PathVariable(name = "account-id") String accountId,
                                                                                  @ParameterObject Pageable pageable);

    @GetMapping("/{post-id}")
    ResponseEntity<GeneralResponse<GetPostDetailResponse>> getPostDetail(@PathVariable(name = "post-id") String postId);

    @DeleteMapping("/{post-id}")
    ResponseEntity<GeneralResponse<Object>> deletePost(@PathVariable(name = "post-id") String postId);

    @GetMapping("/search")
    ResponseEntity<GeneralResponse<List<GetPostDetailResponse>>> searchPostByName(@RequestParam(name = "post-name", required = false) String postName);


}
