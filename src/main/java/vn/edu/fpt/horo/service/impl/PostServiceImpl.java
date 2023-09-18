package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.dto.request.post.UpdatePostRequest;
import vn.edu.fpt.horo.dto.response.post.CreatePostResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.PostMapper;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.repository.AdvisorRepository;
import vn.edu.fpt.horo.repository.PostRepository;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.service.PostService;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * vn.edu.fpt.accounts.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final AccountRepository accountRepository;

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FileService fileService;
    private final AdvisorRepository advisorRepository;


    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        Poster poster = postMapper.mapCreatePostMapper(request);
        try {
            poster = postRepository.save(poster);
            log.info("Save post success");
        } catch (Exception ex) {
            log.error("Error when save post to database: {}", ex.getMessage());
            throw new BusinessException("Can't save post to database: " + ex.getMessage());
        }

        return CreatePostResponse.builder()
                .postId(poster.getPostId())
                .build();
    }


    @Override
    public void updatePost(String postId, UpdatePostRequest request) {
        Poster poster = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Post ID not exist"));
        boolean isUpdated = false;
        if (Objects.nonNull(request.getTitle())) {
            poster.setTitle(request.getTitle());
            poster.setImagePost(request.getImagePost() == null ? null : fileService.getFileById(request.getImagePost()));
            isUpdated = true;
        }
        if (isUpdated) {
            log.info("Post is updated");
            try {
                poster = postRepository.save(poster);
            } catch (Exception ex) {
                throw new BusinessException("Can't update post in database: " + ex.getMessage());
            }
        }
    }

    @Override
    public void deletePost(String postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Post ID not exist"));
        try {
            postRepository.deleteById(postId);
            log.info("Delete post success: {}", postId);
        } catch (Exception ex) {
            throw new BusinessException("Can't delete post by ID: " + ex.getMessage());
        }
    }

    @Override
    public void likedPost(String postId) {
        Poster poster = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Post ID not exist"));
        List<Account> likedUsers = poster.getLikedUsers();
        String accountId = AuditorUtils.getUserIdInToken();
        Integer currentliked = poster.getLiked();
        if (likedUsers.stream().anyMatch(account -> account.getAccountId().equals(accountId))) {
            poster.setLiked(currentliked - 1);
            likedUsers.remove(accountRepository.findAccountByAccountId(accountId).get());
        } else {
            poster.setLiked(currentliked + 1);
            likedUsers.add(accountRepository.findAccountByAccountId(accountId).get());
        }
        poster.setLikedUsers(likedUsers);
        try {
            postRepository.save(poster);
            log.info("liked success");
        } catch (Exception ex) {
            throw new BusinessException("liked fail: " + ex.getMessage());
        }
    }

    @Override
    public GetPostDetailResponse getPostDetail(String postId) {

        Poster poster = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Post ID not exist"));

        Integer currentView = poster.getViews();
        poster.setViews(currentView + 1);
        try {
            postRepository.save(poster);
            log.info("Update post views success");
        } catch (Exception ex) {
            throw new BusinessException("Can't update post views: " + ex.getMessage());
        }
        return postMapper.mapGetPostDetaiResponse(poster);
    }

    @Override
    public Page<GetPostResponse> getPost(Integer page, Integer size, String sort) {
        Page<Poster> posters;
        if (sort == null) {
            sort = "createdDate";
        }
        if (sort.equals("follows")) {
            String accountId = AuditorUtils.getUserIdInToken();
            Optional<Account> account = accountRepository.findAccountByAccountId(accountId);
            List<Advisor> advisors = account.get().getFollowing();
            List<Account> accounts = advisors.stream().map(Advisor::getAccount)
                    .collect(Collectors.toList());
            posters = postRepository.findAllByCreatedByIn(accounts, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate"))));
        } else {
            posters = postRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc(sort))));
        }
        if (posters.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(page, size), 0);
        } else {
            for (Poster poster : posters) {
                GetPostResponse.builder()
                        .countComments(poster.getComments().size())
                        .build();
            }
            return posters.map(postMapper::mapPostResponse);
        }

    }

    @Override
    public List<GetPostDetailResponse> searchPostByName(String postName) {
        List<GetPostDetailResponse> getPostDetailResponses = new ArrayList<>();
        List<Poster> posters = postRepository.findPostByName(postName);
        for (Poster poster : posters) {
            if (poster != null) {
                getPostDetailResponses.add(postMapper.mapGetPostDetaiResponse(poster));
            }
        }
        return getPostDetailResponses;
    }

    @Override
    public Page<GetPostDetailResponse> getPostByAccountId(String accountId, Pageable pageable) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account ID not exist"));
        Page<Poster> postResponses = postRepository.findAllByCreatedBy_AccountId(accountId, pageable);
        return postResponses.map(postMapper::mapGetPostDetaiResponse);
    }

}
