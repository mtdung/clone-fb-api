package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.answer.UpdateAnswerRequest;
import vn.edu.fpt.fb.factory.ResponseFactory;

/**
 * vn.edu.fpt.horo.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class AnswerControllerImpl implements AnswerController {
    private final AnswerService answerService;
    private final ResponseFactory responseFactory;
    @Override
    public ResponseEntity<GeneralResponse<Object>> updateAnswer(String answerId, UpdateAnswerRequest request) {
        answerService.updateAnswer(answerId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }


    @Override
    public ResponseEntity<GeneralResponse<Object>> deleteAnswer(String answerId, String commentId) {
        answerService.deleteAnswer(answerId, commentId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }
}
