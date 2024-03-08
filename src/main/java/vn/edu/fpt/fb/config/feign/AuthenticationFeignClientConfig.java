package vn.edu.fpt.fb.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * vn.edu.fpt.accounts.config.feign
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Configuration
public class AuthenticationFeignClientConfig {

    @Value("${feign.authentication.token}")
    private String token;
//    @Bean
//    public RequestInterceptor requestLoanProductInterceptor() {
//        return requestTemplate -> {
//            requestTemplate.header("Authorization", "Bearer " + token);
//        };
//    }
}
