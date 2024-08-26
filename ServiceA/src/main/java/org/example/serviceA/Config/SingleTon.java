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
        return "http://175.45.204.119:5151/auth/check";
//        return "http://localhost:5151/auth/check";
    }
}
