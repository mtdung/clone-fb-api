package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.answer.UpdateAnswerRequest;
import vn.edu.fpt.horo.entity.Answer;
import vn.edu.fpt.horo.entity.Comment;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.AnswerRepository;
import vn.edu.fpt.horo.repository.CommentRepository;
import vn.edu.fpt.horo.service.AnswerService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * vn.edu.fpt.horo.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;


    @Override
    public void deleteAnswer(String answerId, String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Comment ID not exist"));
        List<Answer> answers = comment.getAnswers();
        Optional<Answer> answer = answers.stream().filter(v -> v.getAnswerId().equals(answerId)).findAny();
        if (answer.isEmpty()) {
            throw new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Answer id not found removeAn");
        }
        if (answers.remove(answer.get())) {
            comment.setAnswers(answers);
            try {
                commentRepository.save(comment);
                log.info("Remove answer from comment success");
                answerRepository.delete(answer.get());
            } catch (Exception ex) {
                throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't update comment in database after remove answer");
            }
        } else {
            throw new BusinessException(ResponseStatusEnum.INTERNAL_SERVER_ERROR, "Can't remove answer from comment");
        }
    }

    @Override
    public void updateAnswer(String answerId, UpdateAnswerRequest request) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "answer ID not exist"));
        if(Objects.nonNull(request.getContent())) {
            answer.setContent(request.getContent());
            try {
                answerRepository.save(answer);
                log.info("Update answer success");
            }catch (Exception ex){
                throw new BusinessException("Can't update answer: "+ ex.getMessage());
            }
        }else{
            log.info("No data must update");
        }
    }
}
