package vn.edu.fpt.horo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.promotion.CreatePromotionRequest;
import vn.edu.fpt.horo.dto.request.promotion.UpdatePromotionRequest;
import vn.edu.fpt.horo.dto.response.promotion.CreatePromotionResponse;
import vn.edu.fpt.horo.dto.response.promotion.GetPromotionResponse;


/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequestMapping("${app.application-context}/public/api/v1/promotions")
public interface PromotionController {

    @PostMapping
    ResponseEntity<GeneralResponse<CreatePromotionResponse>> createPromotion(@RequestBody CreatePromotionRequest request);

    @GetMapping
    ResponseEntity<GeneralResponse<Page<GetPromotionResponse>>> getPromotion(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    );

    @PutMapping("/{promotion-id}")
    ResponseEntity<GeneralResponse<Object>> updatePromotion(@PathVariable(name = "promotion-id")String promotionId,
                                                            @RequestBody UpdatePromotionRequest request);
}
