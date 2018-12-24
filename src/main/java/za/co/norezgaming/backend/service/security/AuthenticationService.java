package za.co.norezgaming.backend.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.repository.account.AccountRepo;
import za.co.norezgaming.backend.service.security.models.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final AccountRepo accountRepo;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AccountRepo accountRepo, JwtTokenProvider jwtTokenProvider){
        this.accountRepo = accountRepo;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public GenericResponseResource authenticate(String username, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken( username, password, new ArrayList<>());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Optional<Account> optionalAccount = accountRepo.findByGamerTag(username);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            return GenericResponseResource.builder().message(jwtTokenProvider.createToken(account.getId().toString(), username, account.getRole().toString())).success(true).build();
        }
        return GenericResponseResource.builder().success(false).message("Account does not exist").build();
    }
}
