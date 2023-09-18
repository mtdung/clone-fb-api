package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.rate.CreateRateRequest;
import vn.edu.fpt.horo.dto.response.booking.NumberBookingResponse;
import vn.edu.fpt.horo.dto.response.rate.CreateRateResponse;
import vn.edu.fpt.horo.dto.response.rate.NumberRateResponse;

/**
 * vn.edu.fpt.horo.service
 *
 * @author : Portgas.D.Ace
 * @created : 29/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

public interface RateService {
    CreateRateResponse createRateAdvisor(CreateRateRequest request);

}
