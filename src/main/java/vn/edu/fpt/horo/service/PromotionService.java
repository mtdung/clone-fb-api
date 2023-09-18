package vn.edu.fpt.horo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.promotion.CreatePromotionRequest;
import vn.edu.fpt.horo.dto.request.promotion.UpdatePromotionRequest;
import vn.edu.fpt.horo.dto.response.promotion.CreatePromotionResponse;
import vn.edu.fpt.horo.dto.response.promotion.GetPromotionResponse;
import vn.edu.fpt.horo.entity.Promotion;
import vn.edu.fpt.horo.entity.PromotionCondition;
import vn.edu.fpt.horo.mapper.PromotionMapper;
import vn.edu.fpt.horo.repository.PromotionRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    public CreatePromotionResponse createPromotion(CreatePromotionRequest request) {
        List<PromotionCondition> promotionConditions = request.getConditions()
                .stream()
                .filter(Objects::nonNull)
                .map(promotionMapper::mapPromotionCondition)
                .collect(Collectors.toList());
        Promotion promotion = promotionMapper.mapCreatePromotionRequest(request);
        promotion.setConditions(promotionConditions);
        promotionRepository.save(promotion);
        return promotionMapper.mapCreatePromotionResponse(promotion);
    }

    public Page<GetPromotionResponse> getPromotion(Integer page, Integer size) {
        Page<Promotion> promotions = promotionRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate"))));
        return promotions.map(promotionMapper::mapPromotionResponse);
    }

    public void updatePromotion(String promotionId, UpdatePromotionRequest request) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow();
        promotionMapper.updatePromotion(promotion, request);
        promotionRepository.save(promotion);
    }
}
