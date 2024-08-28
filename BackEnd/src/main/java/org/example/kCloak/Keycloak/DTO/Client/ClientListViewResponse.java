package org.example.kCloak.Keycloak.DTO.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientListViewResponse {
    private String id;
    private String clientId;
    private String name;
    private String description;
}
