package org.example.kCloak.Keycloak.DTO.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSecretResponse {
    private String type;
    private String value;
}
