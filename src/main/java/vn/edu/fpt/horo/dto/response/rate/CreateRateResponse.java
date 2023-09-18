package vn.edu.fpt.horo.dto.response.rate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.response.rate
 *
 * @author : Portgas.D.Ace
 * @created : 29/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateRateResponse implements Serializable {
    private static final long serialVersionUID = 7218094581047666952L;
    private String rateId;
}
