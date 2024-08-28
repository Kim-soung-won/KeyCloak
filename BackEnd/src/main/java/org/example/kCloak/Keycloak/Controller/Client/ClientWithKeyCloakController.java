package org.example.kCloak.Keycloak.Controller.Client;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Keycloak.DTO.Auth.TokenResponseDTO;
import org.example.kCloak.Keycloak.DTO.Client.*;
import org.example.kCloak.Keycloak.Entity.KeyCloakClient;
import org.example.kCloak.Keycloak.Service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientWithKeyCloakController {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final ClientService clientService;

    @PostMapping("/we-auth/api/create/client")
    // 클라이언트 생성
    public ResponseEntity<?> createClient(@RequestBody CreateClientRequest request,
                                             @RequestHeader("Authorization") String authorization) {
        System.out.println("request : " + request);
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<CreateClientRequest> entity = new HttpEntity<>(request , headers);

        try{
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponseDTO.class);
            System.out.println("response : " + response);
            if(response.getStatusCode().is2xxSuccessful()){
                String secret = checkClientSecret(authorization, request.getId());
                if(secret != null) {
                    KeyCloakClient client = clientService.createClient(request, secret);
                    System.out.println(client);
                }
                return ResponseEntity.ok().body(new CreateClientResponse());
            }
            return ResponseEntity.ok().body(new CreateClientResponse());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/we-auth/api/update/client/{clientUUID}")
    // 클라이언트 수정
    public ResponseEntity<?> updateClient(@RequestHeader("Authorization") String authorization,
                                          @PathVariable String clientUUID,
                                          @RequestBody ClientModiRequest request) {
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients/"+clientUUID;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<ClientModiRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                clientService.updateClient(clientUUID, request);
            }
            System.out.println("response : " + response);
            return response;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/we-auth/api/clients/list")
    // 소속 Realm의  클라이언트 목록 조회
    public ResponseEntity<List<ClientListViewResponse>> listClients(@RequestHeader("Authorization") String authorization) {
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<ClientListViewResponse>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<ClientListViewResponse>>() {});
            System.out.println("response : " + response);
            return response;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/we-auth/api/client/detail/{clientUUID}")
    // 해당 클라이언트 상세 조회
    public ResponseEntity<?> clientDetail(@RequestHeader("Authorization") String authorization,
                                          @PathVariable String clientUUID) {
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients/"+clientUUID;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    Object.class);
            System.out.println("response : " + response);
            return response;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/we-auth/api/delete/client/{clientUUID}")
    // 클라이언트 제거
    public ResponseEntity<?> listClients(@RequestHeader("Authorization") String authorization,
                                                                    @PathVariable String clientUUID) {
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients/"+clientUUID;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                clientService.deleteClient(clientUUID);
            }
            System.out.println("response : " + response);
            return response;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    // DB에 Secret 저장을 위한 Secret 조회 API 연결
    public String checkClientSecret(String authorization, String clientUUID) {
        System.out.println("clientId : " + clientUUID);
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients/" + clientUUID + "/client-secret";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ClientSecretResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ClientSecretResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("client Secret : " + response.getBody().getValue());
                return response.getBody().getValue();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "aa";
        }
        return null;
    }
}
