package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.topic.CreateTopicResponse;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;

import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public interface TopicService {
    CreateTopicResponse createTopic(CreateTopicRequest request);

    List<GetTopicResponse> getTopics();

    void offTopic(String topicId);
}
