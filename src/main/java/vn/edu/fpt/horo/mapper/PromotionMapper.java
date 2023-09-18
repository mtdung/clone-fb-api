package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.edu.fpt.horo.dto.request.promotion.PromotionConditionDTO;
import vn.edu.fpt.horo.dto.request.promotion.CreatePromotionRequest;
import vn.edu.fpt.horo.dto.request.promotion.UpdatePromotionRequest;
import vn.edu.fpt.horo.dto.response.promotion.CreatePromotionResponse;
import vn.edu.fpt.horo.dto.response.promotion.GetPromotionResponse;
import vn.edu.fpt.horo.entity.Promotion;
import vn.edu.fpt.horo.entity.PromotionCondition;
import vn.edu.fpt.horo.service.FileService;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {
    Promotion mapCreatePromotionRequest(CreatePromotionRequest request);

    PromotionCondition mapPromotionCondition(PromotionConditionDTO conditionDTO);

    CreatePromotionResponse mapCreatePromotionResponse(Promotion promotion);

    PromotionConditionDTO mapConditionDTO(PromotionCondition condition);

    GetPromotionResponse mapPromotionResponse(Promotion promotion);

    void updatePromotion(@MappingTarget Promotion promotion, UpdatePromotionRequest request);
}
