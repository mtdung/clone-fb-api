package vn.edu.fpt.horo.service.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author : Cong Dung
 * @product : Horo System
 * @project : Notification Service
 * @created : 11/04/2023 - 21:19
 * @contact : 0339850697 - congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SendNotificationRequest {

    private String userId;
    private String title;
    private String body;
    private Map<String, String> params;
    @Builder.Default
    private String image = "";
}
