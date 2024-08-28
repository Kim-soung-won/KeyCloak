package org.example.kCloak.Keycloak.Repository;

import org.example.kCloak.Keycloak.Entity.KeyCloakClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<KeyCloakClient, Long> {
}
