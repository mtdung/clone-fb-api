package vn.edu.fpt.fb.config.database;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import vn.edu.fpt.fb.config.security.annotation.auditor.SecurityAuditorAware;
import vn.edu.fpt.fb.entity.Account;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.config.database
 **/

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final AccountRepository accountRepository;
    @Bean
    public AuditorAware<Account> auditorAware(){
        return new SecurityAuditorAware(accountRepository);
    }

    @Bean
    public DateTimeProvider utcDateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now());
    }
}
