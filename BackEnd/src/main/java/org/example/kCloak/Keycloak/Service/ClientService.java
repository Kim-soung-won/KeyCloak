package org.example.kCloak.Keycloak.Service;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Keycloak.DTO.Client.CreateClientRequest;
import org.example.kCloak.Keycloak.Entity.KeyCloakClient;
import org.example.kCloak.Keycloak.Repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public KeyCloakClient createClient(CreateClientRequest request, String secret) {
        KeyCloakClient client = KeyCloakClient.builder()
                .clientUUID(request.getId())
                .clientId(request.getClientId())
                .clientName(request.getName())
                .clientDescription(request.getDescription())
                .clientSecret(secret)
                .createdAt(LocalDateTime.now())
                .build();
        return clientRepository.save(client);
    }
}
