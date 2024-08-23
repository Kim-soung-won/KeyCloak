package org.example.serviceA.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthSSO {

    private final String COOKIENAME;
    private final RestTemplate restTemplate;

    // SSO 서버에 요청을 통한 인증 확인
    public boolean authSSO(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        if(cookie==null){
            System.out.println("쿠키가 없어요");
            return false;
        }
        String authToken = null;
        for(Cookie c : cookie){
            if(c.getName().equals(COOKIENAME)){
                authToken = c.getValue();
            }
        }
        System.out.println("authToken = " + authToken);

        String authServiceUrl = "http://localhost:5151/auth/check";
        String response = restTemplate.getForObject(authServiceUrl + "?htoken=" + authToken, String.class);

        if ("valid".equals(response)) {
            System.out.println("인증 성공");
            return true;
        }
        System.out.println("인증 실패");
        return false;
    }
}
