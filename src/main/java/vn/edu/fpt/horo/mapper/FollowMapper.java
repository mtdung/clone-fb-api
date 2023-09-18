package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import vn.edu.fpt.horo.dto.response.follow.GetFollowResponse;
import vn.edu.fpt.horo.entity.Followers;
import vn.edu.fpt.horo.service.FileService;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    GetFollowResponse mappingGetFollow(Followers followers);
}
