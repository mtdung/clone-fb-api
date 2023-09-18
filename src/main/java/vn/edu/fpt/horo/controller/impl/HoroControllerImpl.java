package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.controller.HoroController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.HoroService;
import vn.edu.fpt.horo.service.feign.GetHoroRequest;


@RestController
@RequiredArgsConstructor
public class HoroControllerImpl implements HoroController {

    private final ResponseFactory responseFactory;
    private final HoroService horoService;

    @Override
    public ResponseEntity<GeneralResponse<FileResponse>> checkHoro(GetHoroRequest request) {
        return responseFactory.response(horoService.checkHoro(request));
    }
}
