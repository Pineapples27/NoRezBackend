package za.co.norezgaming.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.service.AccountService;
import za.co.norezgaming.backend.service.security.SecurityService;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final AccountService accountService;
    private final SecurityService securityService;

    @Autowired
    public TestController(AccountService accountService, SecurityService securityService) {
        this.accountService = accountService;
        this.securityService = securityService;
    }

    @GetMapping(value = "/")
    public GenericResponseResource getTestString() {
        return GenericResponseResource.builder().message("Successful Test!").success(true).build();
    }

    @GetMapping(value = "/uuid")
    public GenericResponseResource getUUID() {
        return GenericResponseResource.builder().message(securityService.getAuthentication().toString()).success(true).build();
    }
}
