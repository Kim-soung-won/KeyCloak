package org.example.kCloak.Keycloak.Controller.Client;

import lombok.RequiredArgsConstructor;
import org.example.kCloak.Keycloak.Service.ClientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientWithDBController {

    private final ClientService clientService;

}
