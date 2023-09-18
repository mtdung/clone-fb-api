package vn.edu.fpt.horo.dto.request.service_package;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateServicePackageRequest implements Serializable {
    private static final long serialVersionUID = 6033460984346639737L;

    private String serviceName;
    private BigDecimal price;
    private Integer slot;
}
