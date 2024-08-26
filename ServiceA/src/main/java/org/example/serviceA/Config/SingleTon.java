package org.example.serviceA.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleTon {

    @Bean
    public String COOKIENAME() {
        return "SESSIONID";
    }

    @Bean
    public String AuthURL(){
        return "http://localhost:5151/auth/check";
    }
}
