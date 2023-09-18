package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.dto.request.topic.CreateTopicRequest;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface TopicMapper {
    Topic mapCreateTopicMapper(CreateTopicRequest request);

    GetTopicResponse mapTopicResponse(Topic topic);
}
