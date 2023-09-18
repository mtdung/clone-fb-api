package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.dto.request.post.UpdatePostRequest;
import vn.edu.fpt.horo.dto.response.post.CreatePostResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostResponse;

import java.util.List;

/**
 * vn.edu.fpt.accounts.service
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface PostService {

    CreatePostResponse createPost(CreatePostRequest request);

    void updatePost(String postId, UpdatePostRequest request);

    void deletePost(String postId);

    void likedPost(String postId);

    GetPostDetailResponse getPostDetail(String postId);

    Page<GetPostResponse> getPost(Integer page, Integer size, String sort);

    List<GetPostDetailResponse> searchPostByName(String postName);

    Page<GetPostDetailResponse> getPostByAccountId(String accountId, Pageable pageable);
}
