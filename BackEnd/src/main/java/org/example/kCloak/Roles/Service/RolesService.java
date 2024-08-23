package org.example.kCloak.Roles.Service;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Roles.Entity.Roles;
import org.example.kCloak.Roles.Repository.RolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;

    @Transactional(readOnly = true)
    public Roles findById(Long roleId) {
        return rolesRepository.findById(roleId).orElse(null);
    }
}
