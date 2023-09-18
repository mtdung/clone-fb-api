package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.post.CreatePostResponse;
import vn.edu.fpt.horo.dto.response.topic.CreateTopicResponse;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.TopicMapper;
import vn.edu.fpt.horo.repository.TopicRepository;
import vn.edu.fpt.horo.service.TopicService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequiredArgsConstructor
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public CreateTopicResponse createTopic(CreateTopicRequest request) {
        Topic topic = topicMapper.mapCreateTopicMapper(request);

        try {
            topic = topicRepository.save(topic);
            log.info("Save post success");
        }catch (Exception ex){
            log.error("Error when save topic to database: {}", ex.getMessage());
            throw new BusinessException("Can't save topic to database: "+ ex.getMessage());
        }

        return CreateTopicResponse.builder()
                .topicId(topic.getTopicId())
                .build();

    }

    @Override
    public List<GetTopicResponse> getTopics() {
        return topicRepository.findAllByIsActive(true).stream().map(topicMapper::mapTopicResponse).collect(Collectors.toList());
    }

    @Override
    public void offTopic(String topicId) {
        Topic topic = topicRepository.findByTopicId(topicId);
        topicRepository.updateTopicActive(!topic.isActive(), topicId);
    }
}
