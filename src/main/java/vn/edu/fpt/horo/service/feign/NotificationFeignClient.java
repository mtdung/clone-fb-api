package vn.edu.fpt.horo.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.fpt.horo.config.feign.AuthenticationFeignClientConfig;
import vn.edu.fpt.horo.dto.common.GeneralResponse;

@FeignClient(name = "noti-feign-client", url = "${feign.notification.url}", configuration = AuthenticationFeignClientConfig.class)
public interface NotificationFeignClient {
    @PostMapping("/notifications")
    ResponseEntity<GeneralResponse<Object>> sendNotification(@RequestBody SendNotificationRequest request);

    @PostMapping("/sms")
    ResponseEntity<GeneralResponse<Object>> sendSms(@RequestBody SendSmsRequest request);
}
