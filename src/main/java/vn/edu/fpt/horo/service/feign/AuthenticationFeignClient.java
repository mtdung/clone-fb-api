package vn.edu.fpt.horo.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.fpt.horo.config.feign.AuthenticationFeignClientConfig;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.response.feign.GetAccountIdByUsernameResponse;

/**
 * vn.edu.fpt.accounts.service.feign
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@FeignClient(name = "authentication-feign-client", url = "${feign.authentication.url}", configuration = AuthenticationFeignClientConfig.class)
public interface AuthenticationFeignClient {

    @GetMapping(value = "/accounts/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GeneralResponse<GetAccountIdByUsernameResponse>> getAccountIdsByUsername(@PathVariable(name = "username") String username);
}
