package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.horo.controller.FileController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity._File;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.repository.FileRepository;
import vn.edu.fpt.horo.service.S3BucketStorageService;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileControllerImpl implements FileController {

    private final S3BucketStorageService s3BucketStorageService;
    private final FileRepository fileRepository;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<FileResponse>> uploadFile(MultipartFile request) {
        _File file = _File.builder()
                .fileKey(s3BucketStorageService.uploadFile(request))
                .fileName(request.getOriginalFilename())
                .type(request.getContentType())
                .size(request.getSize())
                .build();
        fileRepository.save(file);
        return responseFactory.response(FileResponse.builder()
                .fileId(file.getFileId())
                .fileName(file.getFileName())
                .type(file.getType())
                .size(file.getSize())
                .publicURL(s3BucketStorageService.sharingUsingPresignedURL(file.getFileKey()))
                .build());
    }
}
