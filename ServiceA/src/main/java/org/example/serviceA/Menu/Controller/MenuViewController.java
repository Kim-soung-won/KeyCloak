package org.example.serviceA.Menu.Controller;

import lombok.RequiredArgsConstructor;
import org.example.serviceA.Config.AuthSSO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MenuViewController {

    private final AuthSSO authSSO;

    @GetMapping("/menu")
    // 인증 확인 테스트
    public ResponseEntity<String> menu(HttpServletRequest request){
        return authSSO.authSSO(request) ? ResponseEntity.ok().body("인증 성공") : ResponseEntity.badRequest().body("인증 실패");
    }
}
