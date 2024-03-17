package vn.edu.fpt.fb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.edu.fpt.fb.service.inter.AuthenticationService;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class UserServiceApplication{
    @Autowired
    AuthenticationService authenticationService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDefaultData(){
        authenticationService.initRole();
        authenticationService.initUser();
    }

}
