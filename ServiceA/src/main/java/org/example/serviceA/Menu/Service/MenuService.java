package org.example.serviceA.Menu.Service;

import lombok.RequiredArgsConstructor;
import org.example.serviceA.Menu.Entity.Menu;
import org.example.serviceA.Menu.Repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public Menu findMenu(){
        return menuRepository.findById(1L).orElse(null);
    }
}
