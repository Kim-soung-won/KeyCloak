package org.example.kCloak.Keycloak.DTO.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String grantType;
    private String clientId;
    private String username;
    private String password;
}
