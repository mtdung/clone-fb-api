package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.dto.common.CreateFileRequest;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Profile;
import vn.edu.fpt.horo.entity._File;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.repository.ProfileRepository;
import vn.edu.fpt.horo.service.FileService;
import vn.edu.fpt.horo.service.HoroService;
import vn.edu.fpt.horo.service.S3BucketStorageService;
import vn.edu.fpt.horo.service.feign.GetHoroRequest;
import vn.edu.fpt.horo.service.feign.HoroFeignClient;
import vn.edu.fpt.horo.utils.AuditorUtils;


@Service
@RequiredArgsConstructor
public class HoroServiceImpl implements HoroService {

    private final HoroFeignClient horoFeignClient;
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final FileService fileService;
    private final S3BucketStorageService s3BucketStorageService;

    @Override
    public FileResponse checkHoro(GetHoroRequest request) {
        Account account = accountRepository.findById(AuditorUtils.getUserIdInToken())
                .orElseThrow();
        Profile profile = account.getProfile();
        request.setAnh_mau("1");
        request.setLuutru("1");
        request.setCanh_bao("0");
        request.setLoai_lich("1");
        ResponseEntity<String> response = horoFeignClient.getHoro(request);
        Document document = Jsoup.parse(response.getBody());
        Element element = document.getElementById("aoc_result_img");
        String base64 = element.attributes().get("src");
        _File file = s3BucketStorageService.uploadFile(CreateFileRequest.builder()
                        .mimeType(MediaType.IMAGE_JPEG_VALUE)
                        .name(account.getFullName()+"_horo")
                        .base64(base64)
                .build());

        profile.setHoro(file);
        profileRepository.save(profile);
        return fileService.getFileResponse(file);
    }
}
