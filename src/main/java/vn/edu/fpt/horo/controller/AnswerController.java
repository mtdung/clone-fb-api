package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.answer.UpdateAnswerRequest;
import vn.edu.fpt.horo.dto.request.comment.UpdateCommentRequest;

/**
 * vn.edu.fpt.horo.controller
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequestMapping("${app.application-context}/public/api/v1/answers")
public interface AnswerController {
    @PutMapping("/{answer-id}")
    ResponseEntity<GeneralResponse<Object>> updateAnswer(@PathVariable(name = "answer-id") String answerId, @RequestBody UpdateAnswerRequest request);

    @DeleteMapping("/{answer-id}/{comment-id}")
    ResponseEntity<GeneralResponse<Object>> deleteAnswer(@PathVariable(name = "answer-id") String answerId, @PathVariable(name = "comment-id") String commentId);
}
