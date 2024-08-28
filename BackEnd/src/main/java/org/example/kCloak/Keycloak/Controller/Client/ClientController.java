package org.example.kCloak.Keycloak.Controller.Client;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Keycloak.DTO.Auth.TokenResponseDTO;
import org.example.kCloak.Keycloak.DTO.Client.ClientSecretResponse;
import org.example.kCloak.Keycloak.DTO.Client.CreateClientRequest;
import org.example.kCloak.Keycloak.DTO.Client.CreateClientResponse;
import org.example.kCloak.Keycloak.Entity.KeyCloakClient;
import org.example.kCloak.Keycloak.Service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final ClientService clientService;

    @PostMapping("/we-auth/api/create/client")
    public ResponseEntity<?> createClient(@RequestBody CreateClientRequest request,
                                             @RequestHeader("Authorization") String authorization) {
        System.out.println("request : " + request);
        String url = keycloakUrl + "/admin/realms/"+ realm +"/clients";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorization);

        HttpEntity<CreateClientRequest> entity = new HttpEntity<>(request , headers);

        try{
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponseDTO.class);
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
