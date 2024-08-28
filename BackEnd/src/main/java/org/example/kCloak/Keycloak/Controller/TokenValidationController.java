package org.example.kCloak.Keycloak.Controller;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Keycloak.DTO.Auth.LoginRequestDTO;
import org.example.kCloak.Keycloak.DTO.Auth.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TokenValidationController {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private final String keycloakUrl;

    @Value("${keycloak.realm}")
    private final String realm;

    @GetMapping("/we-auth/api/auth/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String Authorization) {
        // 토큰 유효성 검사
        // userinfo endpoint를 호출하여 토큰의 유효성을 검사한다.
        // scope=profile로 호출하여 사용자 정보를 가져온다.
        // master realm에 대한 userinfo endpoint 호출
        // protocol/openid-connect 로 OpenID Connect 사용
        String url = keycloakUrl + "/realms/"+ realm +"/protocol/openid-connect/userinfo?scope=email-profile";
        System.out.println("url : "+url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + Authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String,Object>>() {});
            System.out.println("response : "+ response);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("fail");
        }
    }

    @PostMapping("/we-auth/api/login")
    // 로그인 수행 후 Access Token 발급
    public ResponseEntity<String> loginKeycloak(@RequestBody LoginRequestDTO request) {
        System.out.println(request.getClientId());
        String url = keycloakUrl + "/realms/"+ realm +"/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", request.getGrantType());
        body.add("client_id", request.getClientId());
        body.add("username", request.getUsername());
        body.add("password", request.getPassword());


        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(body , headers);

        try {
            ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponseDTO.class);
            System.out.println("response : "+response.getBody().getAccess_token());
            return ResponseEntity.ok().body(response.getBody().getAccess_token());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("fail");
        }
    }
}
