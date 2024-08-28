package org.example.kCloak.Keycloak.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class RolesController {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @GetMapping("/we-auth/api/roles/realm")
    // 해당 Realm이 갖는 모든 Role 목록을 가져온다.
    public ResponseEntity<?> getRoles(@RequestHeader("Authorization") String authorization) {
        String url = keycloakUrl + "/admin/realms/"+ realm +"/roles";
        System.out.println("url : "+url);

        System.out.println("Authorization : "+authorization);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("response : "+response);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
