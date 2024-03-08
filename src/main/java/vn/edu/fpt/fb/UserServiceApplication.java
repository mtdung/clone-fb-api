package vn.edu.fpt.fb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * vn.edu.fpt.accounts
 *
 * @author : Portgas.D.Ace
 * @created : 16/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@EnableFeignClients
@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Horo System ", version = "1.0"),
        servers = {@Server(url = "/", description = "Default Server URL")})
public class UserServiceApplication{
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDefaultData(){
        roleService.init();
        accountService.init();
    }



}
