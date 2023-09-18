package vn.edu.fpt.horo.dto.response.promotion;

import lombok.*;
import vn.edu.fpt.horo.constant.PromotionStatus;
import vn.edu.fpt.horo.dto.request.promotion.PromotionConditionDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetPromotionResponse {

    private String promotionId;
    private String promotionName;
    private String description;
    private List<PromotionConditionDTO> conditions;
    private BigDecimal discount;
    private BigDecimal maxDiscount;
    private BigDecimal budget;
    private PromotionStatus status;
}
