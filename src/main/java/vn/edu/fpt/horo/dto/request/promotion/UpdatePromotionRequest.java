package vn.edu.fpt.horo.dto.request.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * vn.edu.fpt.accounts.controller
 *
 * @author : Portgas.D.Ace
 * @created : 26/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdatePromotionRequest {

    private String promotionName;
    private String description;
    private List<PromotionConditionDTO> conditions;
    private BigDecimal discount;
    private BigDecimal maxDiscount;
    private BigDecimal budget;
}
