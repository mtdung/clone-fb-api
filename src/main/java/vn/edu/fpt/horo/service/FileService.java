package vn.edu.fpt.horo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity._File;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.FileRepository;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final S3BucketStorageService s3BucketStorageService;

    public _File getFileById(String id) {
        if (id == null) {
            return null;
        }
        return fileRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "File id not found: " + id));
    }

    public FileResponse getFileResponse(_File file){
        if (file == null) {
            return null;
        }
        return FileResponse.builder()
                .fileId(file.getFileId())
                .fileName(file.getFileName())
                .size(file.getSize())
                .type(file.getType())
                .publicURL(s3BucketStorageService.sharingUsingPresignedURL(file.getFileKey()))
                .build();
    }
}
