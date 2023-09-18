package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;

import vn.edu.fpt.horo.dto.request.rate.CreateRateRequest;

import vn.edu.fpt.horo.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.horo.dto.response.rate.NumberRateResponse;

/**
 * vn.edu.fpt.horo.controller.impl
 *
 * @author : Portgas.D.Ace
 * @created : 29/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequestMapping("${app.application-context}/public/api/v1/rates")
public interface RateController {
    @PostMapping("/rate")
    ResponseEntity<GeneralResponse<CreateRateResponse>> createRateAdvisor(@RequestBody CreateRateRequest request);

}
