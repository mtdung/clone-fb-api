package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.service.feign.GetHoroRequest;


public interface HoroService {

    FileResponse checkHoro(GetHoroRequest request);
}
