package org.example.serviceA.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieName {

    @Bean
    public String COOKIENAME() {
        return "SESSIONID";
    }
}
