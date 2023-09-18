package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.service.feign.GetHoroRequest;


@RequestMapping("${app.application-context}/public/api/v1/horo")
public interface HoroController {

    @PostMapping
    ResponseEntity<GeneralResponse<FileResponse>> checkHoro(@RequestBody GetHoroRequest request);
}
