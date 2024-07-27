package com.sparklecow.cowradio.config.auditing;

import com.sparklecow.cowradio.entities.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditingAwareConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> {
            Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }
            User userPrincipal = (User) authentication.getPrincipal();
            return Optional.ofNullable(userPrincipal.getUsername());
        };
    }
}