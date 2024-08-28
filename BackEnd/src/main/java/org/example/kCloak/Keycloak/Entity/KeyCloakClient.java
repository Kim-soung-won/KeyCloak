package org.example.kCloak.Keycloak.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyCloakClient {
    @Id
    @Column(name = "client_uuid")
    private String clientUUID;

    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_description")
    private String clientDescription;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_who")
    private String createdWho;
}
