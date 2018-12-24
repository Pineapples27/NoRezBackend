package za.co.norezgaming.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.controller.requestModels.CreateAccountModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.repository.account.AccountRepo;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public GenericResponseResource createAccount(CreateAccountModel createAccountModel){
        if(checkIfUnique(createAccountModel.getGamerTag())){
            accountRepo.save(Account.builder().gamerTag(createAccountModel.getGamerTag()).password(createAccountModel.getPassword()).role(createAccountModel.getRole()).build());
            return GenericResponseResource.builder().success(true).message("Account for " + createAccountModel.getGamerTag() + " successfully created!").build();
        }
        return GenericResponseResource.builder().success(false).message("Username not unique").build();
    }

    private boolean checkIfUnique(String gamerTag){
        Optional<Account> optional = accountRepo.findByGamerTag(gamerTag);
        return !optional.isPresent();
    }
}
