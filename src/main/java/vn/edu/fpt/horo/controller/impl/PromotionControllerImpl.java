package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.PromotionController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.promotion.CreatePromotionRequest;
import vn.edu.fpt.horo.dto.request.promotion.UpdatePromotionRequest;
import vn.edu.fpt.horo.dto.response.promotion.CreatePromotionResponse;
import vn.edu.fpt.horo.dto.response.promotion.GetPromotionResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.PromotionService;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
public class PromotionControllerImpl implements PromotionController {

    private final ResponseFactory responseFactory;
    private final PromotionService promotionService;

    @Override
    public ResponseEntity<GeneralResponse<CreatePromotionResponse>> createPromotion(CreatePromotionRequest request) {
        return responseFactory.response(promotionService.createPromotion(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<GetPromotionResponse>>> getPromotion(Integer page, Integer size) {
        return responseFactory.response(promotionService.getPromotion(page, size));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updatePromotion(String promotionId, UpdatePromotionRequest request) {
        promotionService.updatePromotion(promotionId, request);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }
}
