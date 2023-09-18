package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.common.ProfileResponse;
import vn.edu.fpt.horo.dto.request.profile.CreateProfileRequest;
import vn.edu.fpt.horo.dto.request.profile.GetProfileBookingRespone;
import vn.edu.fpt.horo.entity.Profile;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface ProfileMapper {
    Profile mapCreateProfileToProfile(CreateProfileRequest request);

}
