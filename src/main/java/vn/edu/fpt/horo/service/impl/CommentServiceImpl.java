package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToCommentResponse;
import vn.edu.fpt.horo.dto.response.comment.AddCommentToPostResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Answer;
import vn.edu.fpt.horo.entity.Comment;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.AnswerMapper;
import vn.edu.fpt.horo.mapper.CommentMapper;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.repository.AnswerRepository;
import vn.edu.fpt.horo.repository.CommentRepository;
import vn.edu.fpt.horo.repository.PostRepository;
import vn.edu.fpt.horo.service.CommentService;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final AnswerMapper answerMapper;
    private final AccountRepository accountRepository;

    @Override
    public AddCommentToPostResponse addCommentToPost(String postId, AddCommentToPostRequest request) {

        Poster poster = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Post ID not exist"));
        Comment comment = commentMapper.mapAddCommentToPost(request);
        List<Comment> comments = poster.getComments();

        try {
            comment = commentRepository.save(comment);
        }catch (Exception ex){
            throw new BusinessException("Can't create comment: "+ ex.getMessage());
        }
        comments.add(comment);
        poster.setComments(comments);
        postRepository.save(poster);
        return AddCommentToPostResponse.builder()
                .commentId(comment.getCommentId())
                .build();
    }

    @Override
    public void deleteComment(String commentId, String postId) {
        Poster poster = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment ID not exist"));
        List<Comment> comments = poster.getComments();
        Optional<Comment> comment = comments.stream().filter(v -> v.getCommentId().equals(commentId)).findAny();
        if (comment.isEmpty()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment id not found removeCommentToPost");
        }
        if (comments.remove(comment.get())) {
            poster.setComments(comments);
            try {
                postRepository.save(poster);
                log.info("Remove comment from Post success");
                commentRepository.delete(comment.get());
            } catch (Exception ex) {
                throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't update post in database after remove comment");
            }
        } else {
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't remove comment from post");
        }
    }
    @Override
    public void updateComment(String commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment ID not exist"));
        if(Objects.nonNull(request.getContent())) {
            comment.setContent(request.getContent());
            try {
                commentRepository.save(comment);
                log.info("Update comment success");
            }catch (Exception ex){
                throw new BusinessException("Can't update comment: "+ ex.getMessage());
            }
        }else{
            log.info("No data must update");
        }
    }

    @Override
    public AddCommentToCommentResponse addCommentToComment(String commentId, AddCommentToPostRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment ID not exist"));
        Answer answer = answerMapper.mapAddCommentToComment(request);
        List<Answer> answers = comment.getAnswers();
        try {
            answer = answerRepository.save(answer);
        }catch (Exception ex){
            throw new BusinessException("Can't create comment: "+ ex.getMessage());
        }
        answers.add(answer);
        comment.setAnswers(answers);
        commentRepository.save(comment);
        return AddCommentToCommentResponse.builder()
                .answerId(answer.getAnswerId())
                .build();

    }

    @Override
    public void likedComment(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment ID not exist"));
        List<Account> likedUsers = comment.getLikedUsers();
        String accountId = AuditorUtils.getUserIdInToken();
        Integer currentliked = comment.getLiked();
        if(likedUsers.stream().anyMatch(account -> account.getAccountId().equals(accountId))){
            comment.setLiked(currentliked-1);
            likedUsers.remove(accountRepository.findAccountByAccountId(accountId).get());
        }else{
            comment.setLiked(currentliked+1);
            likedUsers.add(accountRepository.findAccountByAccountId(accountId).get());
        }
        comment.setLikedUsers(likedUsers);
        try {
            commentRepository.save(comment);
            log.info("liked success");
        }catch (Exception ex){
            throw new BusinessException("liked fail: "+ ex.getMessage());
        }
    }

    @Override
    public void likedAnswer(String answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(()-> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "answer ID not exist"));
        List<Account> likedUsers = answer.getLikedUsers();
        String accountId = AuditorUtils.getUserIdInToken();
        Integer currentliked = answer.getLiked();
        if(likedUsers.stream().anyMatch(account -> account.getAccountId().equals(accountId))){
            answer.setLiked(currentliked-1);
            likedUsers.remove(accountRepository.findAccountByAccountId(accountId).get());
        }else{
            answer.setLiked(currentliked+1);
            likedUsers.add(accountRepository.findAccountByAccountId(accountId).get());
        }
        answer.setLikedUsers(likedUsers);
        answer.setLikedUsers(likedUsers);
        try {
            answerRepository.save(answer);
            log.info("liked success");
        }catch (Exception ex){
            throw new BusinessException("liked fail: "+ ex.getMessage());
        }
    }


}
