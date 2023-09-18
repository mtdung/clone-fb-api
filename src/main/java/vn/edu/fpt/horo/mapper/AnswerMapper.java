package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import vn.edu.fpt.horo.dto.request.comment.AddCommentToPostRequest;
import vn.edu.fpt.horo.entity.Answer;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.horo.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", uses = {FileService.class})
public interface AnswerMapper {
    Answer mapAddCommentToComment(AddCommentToPostRequest request);
}
