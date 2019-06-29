package za.co.norezgaming.backend.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.norezgaming.backend.controller.requestModels.MediaModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.service.MediaService;

@RestController
@RequestMapping(value = "/media")
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService){
        this.mediaService = mediaService;
    }

    @PostMapping(value = "/add")
    public GenericResponseResource createMedia(@RequestBody MediaModel mediaModel) throws NotFoundException {
        return mediaService.addNewMedia(mediaModel);
    }
}
