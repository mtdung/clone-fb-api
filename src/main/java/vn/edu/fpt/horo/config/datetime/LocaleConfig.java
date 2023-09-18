package vn.edu.fpt.horo.config.datetime;

/**
 * vn.edu.fpt.horo.config.datetime
 *
 * @author : Portgas.D.Ace
 * @created : 27/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class LocaleConfig {

    @PostConstruct
    public void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));

    }

}
