package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.profile.CreateProfileRequest;
import vn.edu.fpt.horo.dto.request.profile.UpdateProfileRequest;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@RequestMapping("${app.application-context}/public/api/v1/profiles")
public interface ProfileController {

    @PostMapping
    ResponseEntity<GeneralResponse<Object>> createProfile(@RequestBody CreateProfileRequest request);

    @PutMapping("/{profile-id}")
    ResponseEntity<GeneralResponse<Object>> updateProfiles(@PathVariable("profile-id") String parameter, @RequestBody UpdateProfileRequest request);

}
