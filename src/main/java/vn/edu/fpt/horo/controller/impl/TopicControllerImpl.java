package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.controller.TopicController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.topic.CreateTopicResponse;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.TopicService;

import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
public class TopicControllerImpl implements TopicController {
    private final ResponseFactory responseFactory;
    private final TopicService topicService;
    @Override
    public ResponseEntity<GeneralResponse<List<GetTopicResponse>>> getTopics() {
        return responseFactory.response(topicService.getTopics());
    }
}
