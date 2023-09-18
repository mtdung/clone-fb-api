package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.request.policy.CreatePolicyRequest;
import vn.edu.fpt.horo.dto.request.post.CreatePostRequest;
import vn.edu.fpt.horo.entity.Policy;
import vn.edu.fpt.horo.entity.Poster;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.horo.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface PolicyMapper {

    Policy mapCreatePolicyMapper(CreatePolicyRequest request);
}
