package za.co.norezgaming.backend.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.norezgaming.backend.controller.requestModels.CreateAccountModel;
import za.co.norezgaming.backend.controller.responseModels.AccountResponse;
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

    @GetMapping(value ="/")
    public AccountResponse getAccount() throws NotFoundException {
        return accountService.getAccount();
    }
}
