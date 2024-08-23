package org.example.kCloak.Roles.Repository;

import org.example.kCloak.Roles.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
