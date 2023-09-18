package vn.edu.fpt.horo.config.security.annotation.auditor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.repository.AccountRepository;
import vn.edu.fpt.horo.utils.AuditorUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.config.security.annotation.auditor
 **/

@RequiredArgsConstructor
public class SecurityAuditorAware implements AuditorAware<Account> {

    private final AccountRepository accountRepository;
    @Override
    public Optional<Account> getCurrentAuditor() {
        String accountId =  Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername).orElse(null);
        if(Objects.nonNull(accountId)){
            return accountRepository.findById(accountId);
        }else{
            return Optional.empty();
        }
    }
}
