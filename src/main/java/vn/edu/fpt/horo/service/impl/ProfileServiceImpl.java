package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.profile.CreateProfileRequest;
import vn.edu.fpt.horo.dto.request.profile.UpdateProfileRequest;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Profile;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.mapper.ProfileMapper;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.repository.FileRepository;
import vn.edu.fpt.horo.repository.ProfileRepository;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.service.ProfileService;
import vn.edu.fpt.horo.service.S3BucketStorageService;
import vn.edu.fpt.horo.service.UserInfoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * vn.edu.fpt.accounts.service.impl
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final AccountRepository accountRepository;
    private final FileRepository fileRepository;

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserInfoService userInfoService;
    private final S3BucketStorageService s3BucketStorageService;
    @Value("${application.account.cloudfront}")
    private String accountCloudFront;
    private final FileService fileService;
    @Override
    public void createProfile(CreateProfileRequest request) {
        Account account = accountRepository.findAccountByAccountId(request.getAccountId())
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Account id not exist"));

        Profile profile = profileMapper.mapCreateProfileToProfile(request);
        try {
            profile = profileRepository.save(profile);
            log.info("Create profile info success");
        } catch (Exception ex) {
            throw new BusinessException("Can't save profile to database: " + ex.getMessage());
        }
        account.setProfile(profile);
        accountRepository.save(account);

    }

    @Override
    public void updateProfile(String profileId, UpdateProfileRequest request) {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Profile ID not exist"));
        if (Objects.nonNull(request.getGender())) {
            profile.setGender(request.getGender());
        }
        if (Objects.nonNull(request.getAddress())) {
            profile.setAddress(request.getAddress());
        }
        if (Objects.nonNull(request.getDateOfBirth())) {
            profile.setDateOfBirth(LocalDateTime.from(LocalDate.from(request.getDateOfBirth())));
        }
        if (Objects.nonNull(request.getPhoneNumber())) {
            profile.setPhoneNumber(request.getPhoneNumber());
        }
        try {
            profileRepository.save(profile);
            log.info("Update profile success");
        } catch (Exception ex) {
            throw new BusinessException("Can't update profile in database: " + ex.getMessage());
        }
    }


}
