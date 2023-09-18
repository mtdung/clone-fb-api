package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostResponse;
import vn.edu.fpt.horo.entity.*;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", uses = {FileService.class})
public interface PostMapper {
    Poster mapCreatePostMapper(CreatePostRequest request);

    @Mapping(target = "countComments", expression = "java(commentCount(poster))")
    GetPostResponse mapPostResponse(Poster poster);


    default Integer commentCount(Poster poster){
        return poster.getComments().size();
    }
    GetPostDetailResponse mapGetPostDetaiResponse(Poster poster);

}

