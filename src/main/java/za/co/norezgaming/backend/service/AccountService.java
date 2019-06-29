package za.co.norezgaming.backend.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.controller.requestModels.CreateAccountModel;
import za.co.norezgaming.backend.controller.responseModels.AccountResponse;
import za.co.norezgaming.backend.controller.responseModels.MediaResponse;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.domain.account.Media;
import za.co.norezgaming.backend.repository.account.AccountRepo;
import za.co.norezgaming.backend.service.security.SecurityService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    @Autowired
    public AccountService(SecurityService securityService, AccountRepo accountRepo, PasswordEncoder passwordEncoder) {
        this.securityService = securityService;
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountResponse getAccount() throws NotFoundException {
        return toAccountResponse(getAccountFromToken());
    }

    private AccountResponse toAccountResponse(Account account){
        return AccountResponse
                .builder()
                .accountUUID(account.getAccountUUID())
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .gamerTag(account.getGamerTag())
                .media(toMediaResponses(account.getMedia()))
                .pointsAccumulated(account.getPointsAccumulated())
                .profilePicture(account.getProfilePicture())
                .role(account.getRole())
                .build();
    }

    private List<MediaResponse> toMediaResponses(List<Media> media){
        return media.stream().map(this::toMediaResponse).collect(Collectors.toList());
    }

    private MediaResponse toMediaResponse(Media media){
        return MediaResponse.builder().url(media.getUrl()).mediaType(media.getMediaType()).build();
    }

    public Account getAccountFromToken() throws NotFoundException {
        return accountRepo.findByAccountUUID(securityService.getAccountUUID().orElseThrow(() -> new NotFoundException("Claim Not Found"))).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public GenericResponseResource createAccount(CreateAccountModel createAccountModel){
        if(checkIfUnique(createAccountModel.getGamerTag())){
            accountRepo.save(Account.builder().gamerTag(createAccountModel.getGamerTag()).password(passwordEncoder.encode(createAccountModel.getPassword())).accountUUID(UUID.randomUUID()).role(createAccountModel.getRole()).build());
            return GenericResponseResource.builder().success(true).message("Account for " + createAccountModel.getGamerTag() + " successfully created!").build();
        }
        return GenericResponseResource.builder().success(false).message("Username not unique").build();
    }

    private boolean checkIfUnique(String gamerTag){
        Optional<Account> optional = accountRepo.findByGamerTag(gamerTag);
        return !optional.isPresent();
    }
}
