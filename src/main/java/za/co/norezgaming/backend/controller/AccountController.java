package za.co.norezgaming.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.norezgaming.backend.controller.requestModels.CreateAccountModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/create")
    public GenericResponseResource createAccount(@RequestBody CreateAccountModel createAccountModel) {
        return accountService.createAccount(createAccountModel);
    }
}
