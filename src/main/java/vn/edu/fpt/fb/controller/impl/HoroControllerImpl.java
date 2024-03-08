package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.response.file.FileResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;


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
