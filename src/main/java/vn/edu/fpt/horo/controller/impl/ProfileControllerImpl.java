package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.ProfileController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.profile.CreateProfileRequest;
import vn.edu.fpt.horo.dto.request.profile.UpdateProfileRequest;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.ProfileService;

/**
 * vn.edu.fpt.accounts.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileControllerImpl implements ProfileController {

    private final ResponseFactory responseFactory;
    private final ProfileService profileService;

    @Override
    public ResponseEntity<GeneralResponse<Object>> createProfile(CreateProfileRequest request) {
        profileService.createProfile(request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateProfiles(String profileId, UpdateProfileRequest request) {
        profileService.updateProfile(profileId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

}
