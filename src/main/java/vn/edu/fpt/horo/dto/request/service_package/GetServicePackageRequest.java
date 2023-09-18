package vn.edu.fpt.horo.dto.request.service_package;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.entity.Topic;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * vn.edu.fpt.accounts.dto.request.service_package
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetServicePackageRequest implements Serializable {

    private static final long serialVersionUID = 4575530979375085870L;
    private String packageServiceId;
    private String serviceName;
    private String description;
    private Topic topic;
    private BigDecimal price;
    private Boolean isActive;
}
