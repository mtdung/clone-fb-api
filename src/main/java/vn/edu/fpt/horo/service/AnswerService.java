package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.answer.UpdateAnswerRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;

/**
 * vn.edu.fpt.horo.service
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface AnswerService {
    void deleteAnswer(String answerId, String commentId);

    void updateAnswer(String answerId, UpdateAnswerRequest request);

}
