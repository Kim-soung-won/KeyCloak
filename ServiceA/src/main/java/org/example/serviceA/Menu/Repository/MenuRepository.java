package org.example.serviceA.Menu.Repository;

import org.example.serviceA.Menu.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
