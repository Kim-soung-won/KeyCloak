package org.example.kCloak.Config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String COOKIENAME;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Cookie cookie = new Cookie(COOKIENAME, request.getSession().getId()); // 세션 ID 또는 인증 토큰
        cookie.setHttpOnly(true); // 클라이언트 측 스크립트에서 접근 불가
        cookie.setPath("/"); // 쿠키의 유효 경로
        cookie.setMaxAge(60 * 60); // 쿠키의 유효 시간 (1시간)
        response.addCookie(cookie); // 응답에 쿠키 추가

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}
