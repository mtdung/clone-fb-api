package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.controller.RateController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.promotion.CreatePromotionRequest;
import vn.edu.fpt.horo.dto.request.rate.CreateRateRequest;
import vn.edu.fpt.horo.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.horo.dto.response.rate.NumberRateResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.RateService;

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
