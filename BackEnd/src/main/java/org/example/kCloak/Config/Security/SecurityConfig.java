//package org.example.kCloak.Config.Security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.io.PrintWriter;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final UserDetailService userDetailService;
//    private final String COOKIENAME;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors((cors) -> cors.configurationSource(request -> {
//                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                            corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
//                            corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
//                            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//                            corsConfiguration.setAllowCredentials(true);
//                            corsConfiguration.setMaxAge(3600L);
//
//                            corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));
//                            return corsConfiguration;
//                    })
//                );
//        http
//                .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .authorizeRequests()
//                    .antMatchers("/signup", "/login", "/okok", "/auth/check", "/logout").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                .userDetailsService(userDetailService)
//                .formLogin()
//                    .loginProcessingUrl("/login") //로그인 시도하는 URL, Page로 하면 안됨
//                    .successHandler(new CustomAuthenticationSuccessHandler(COOKIENAME))
//                    .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .addLogoutHandler(new CustomLogoutHandler(COOKIENAME))
//                    .and();
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    private final AuthenticationEntryPoint unauthorizedEntryPoint =
//            //사용자가 인증되지 않은 상태에서 보호된 리소스에 접근할 때 호출
//            (request, response, authException) -> {
//                //응답을 401로 설정하고 메시지를 담는다.
//                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "로그인을 해주세여");
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                String json = new ObjectMapper().writeValueAsString(fail);
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE); //응답의 타입을 json으로 설정
//                PrintWriter writer = response.getWriter();
//                writer.write(json);
//                writer.flush();
//            };
//
//    private final AccessDeniedHandler accessDeniedHandler =
//            //사용자가 권한이 없는 상태에서 보호된 리소스에 접근할 때 호출
//            (request, response, accessDeniedException) -> {
//                //응답을 403으로 설정하고 메시지를 담는다.
//                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "권한이 없어요");
//                response.setStatus(HttpStatus.FORBIDDEN.value());
//                String json = new ObjectMapper().writeValueAsString(fail);
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                PrintWriter writer = response.getWriter();
//                writer.write(json);
//                writer.flush();
//            };
//
//    @Getter
//    @RequiredArgsConstructor
//    public class ErrorResponse {
//        private final HttpStatus status;
//        private final String message;
//    }
//}
