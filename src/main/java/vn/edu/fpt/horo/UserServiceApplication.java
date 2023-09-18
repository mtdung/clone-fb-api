package vn.edu.fpt.horo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.service.AccountService;
import vn.edu.fpt.horo.service.RoleService;
import vn.edu.fpt.horo.service.feign.GetHoroRequest;
import vn.edu.fpt.horo.service.feign.HoroFeignClient;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Objects;
import java.util.TimeZone;

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
