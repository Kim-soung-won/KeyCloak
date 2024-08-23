package org.example.serviceA.Menu.Controller;

import lombok.RequiredArgsConstructor;
import org.example.serviceA.Config.AuthSSO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MenuViewController {

    private final AuthSSO authSSO;

    @GetMapping("/menu")
    // 인증 확인 테스트
    public String menu(HttpServletRequest request){
        return authSSO.authSSO(request) ? "menu" : "error";
    }
}
