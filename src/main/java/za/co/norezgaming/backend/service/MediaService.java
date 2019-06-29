package za.co.norezgaming.backend.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.controller.requestModels.MediaModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.domain.account.Media;
import za.co.norezgaming.backend.repository.account.AccountRepo;
import za.co.norezgaming.backend.repository.account.MediaRepo;
import za.co.norezgaming.backend.service.security.SecurityService;

import java.util.function.Supplier;

@Service
public class MediaService {
    private final MediaRepo mediaRepo;
    private final AccountService accountService;

    @Autowired
    public MediaService(MediaRepo mediaRepo, AccountService accountService) {
        this.mediaRepo = mediaRepo;
        this.accountService = accountService;
    }

    public GenericResponseResource addNewMedia(MediaModel mediaModel) throws NotFoundException {
        Account account = accountService.getAccountFromToken();
        mediaRepo.save(buildMedia(mediaModel, account));
        return GenericResponseResource.builder().success(true).message("Added media successfully").build();
    }

    private Media buildMedia(MediaModel mediaModel, Account account){
        return Media.builder().account(account).mediaType(mediaModel.getMediaType()).url(mediaModel.getUrl()).build();
    }
}
