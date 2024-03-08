package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.rate.CreateRateRequest;
import vn.edu.fpt.fb.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

/**
 * vn.edu.fpt.horo.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 29/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
public class RateControllerImpl implements RateController {
    private final ResponseFactory responseFactory;
    private final RateService rateService;

    @Override
    public ResponseEntity<GeneralResponse<CreateRateResponse>> createRateAdvisor(CreateRateRequest request) {
        return responseFactory.response(rateService.createRateAdvisor(request));
    }



}
