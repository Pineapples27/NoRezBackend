package za.co.norezgaming.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.service.security.AuthenticationService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping(value = "/login")
    public GenericResponseResource login(@RequestBody NoRezUserCredentials noRezUserCredentials) {
        return authenticationService.authenticate(noRezUserCredentials.getUsername(), noRezUserCredentials.getPassword());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class NoRezUserCredentials {
        private String username;
        private String password;
    }
}
