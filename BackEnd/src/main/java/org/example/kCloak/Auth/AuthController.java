package org.example.kCloak.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth/check")
    public ResponseEntity<String> validateToken() {
        return new ResponseEntity<>("valid", HttpStatus.OK);
    }
}
