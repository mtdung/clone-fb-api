package vn.edu.fpt.horo.dto.request.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.ConditionOperator;
import vn.edu.fpt.horo.constant.ConditionType;

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
public class PromotionConditionDTO {

    private ConditionType type;
    private ConditionOperator operator;
    private List<String> values;
    private String description;
}
