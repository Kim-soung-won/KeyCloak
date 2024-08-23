package org.example.kCloak.Config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final String COOKIENAME;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 쿠키 만료 처리
        System.out.println("Logout!!");
        Cookie cookie = new Cookie(COOKIENAME, null); // 쿠키 값을 null로 설정
        cookie.setHttpOnly(true); // 클라이언트 측 스크립트에서 접근 불가
        cookie.setPath("/"); // 쿠키의 유효 경로
        cookie.setMaxAge(0); // 쿠키 만료
        response.addCookie(cookie); // 응답에 쿠키 추가


        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}
