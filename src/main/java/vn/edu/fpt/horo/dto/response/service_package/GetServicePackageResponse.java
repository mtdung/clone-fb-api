package vn.edu.fpt.horo.dto.response.service_package;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.topic.GetTopicResponse;
import vn.edu.fpt.horo.entity.Topic;

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
public class GetServicePackageResponse implements Serializable {
    private static final long serialVersionUID = 4167350358422884124L;
    private String packageServiceId;
    private String serviceName;
    private GetTopicResponse topic;
    private BigDecimal price;
    private Boolean isActive;
}
