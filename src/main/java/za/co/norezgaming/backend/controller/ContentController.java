package za.co.norezgaming.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.norezgaming.backend.controller.requestModels.CarouselContentModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.service.ContentService;

import java.util.List;

@RestController
@RequestMapping(value = "/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @PostMapping(value = "/carousel")
    public GenericResponseResource createCarouselContent(@RequestBody CarouselContentModel carouselContentModel) {
        return contentService.createCarouselContent(carouselContentModel);
    }

    @GetMapping(value = "/carousel")
    public List<CarouselContentModel> getCarouselContent() {
        return contentService.getCarousel();
    }
}
