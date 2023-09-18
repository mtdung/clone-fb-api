package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
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
@RequestMapping("${app.application-context}/public/api/v1/topics")
public interface TopicController {

    @GetMapping
    ResponseEntity<GeneralResponse<List<GetTopicResponse>>> getTopics();

}
