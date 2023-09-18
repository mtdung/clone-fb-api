package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.profile.CreateProfileRequest;
import vn.edu.fpt.horo.dto.request.profile.UpdateProfileRequest;

/**
 * vn.edu.fpt.accounts.service
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface ProfileService {
    void createProfile(CreateProfileRequest event);
    void updateProfile(String profileId, UpdateProfileRequest request);

}
