package za.co.norezgaming.backend.service;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.controller.requestModels.CarouselContentModel;
import za.co.norezgaming.backend.domain.GenericResponseResource;
import za.co.norezgaming.backend.domain.content.CarouselContent;
import za.co.norezgaming.backend.domain.content.CarouselFeature;
import za.co.norezgaming.backend.repository.content.CarouselContentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private final CarouselContentRepository carouselContentRepository;

    @Autowired
    public ContentService(CarouselContentRepository carouselContentRepository){
        this.carouselContentRepository = carouselContentRepository;
    }

    public List<CarouselContentModel> getCarousel(){
        return buildCarouselResponse(carouselContentRepository.findAll());
    }

    public GenericResponseResource createCarouselContent(CarouselContentModel carouselContentModel){
        String validation = validateCreateCarouselContent(carouselContentModel);
       if(validation != null){
           return GenericResponseResource.builder().message(validation).success(false).build();
       }

       Optional<CarouselContent> optionalCarouselContent = carouselContentRepository.findByCarouselFeature(CarouselFeature.valueOf(carouselContentModel.getCarouselFeature()));
       if(optionalCarouselContent.isPresent()){
           return updateCarouselContent(optionalCarouselContent.get(), carouselContentModel);
       }
       else{
           carouselContentRepository.save(requestToCarouselContent(carouselContentModel));
           return GenericResponseResource.builder().success(true).message("New carousel content saved").build();
       }
    }

    private List<CarouselContentModel> buildCarouselResponse(List<CarouselContent> carouselContentList){
        return carouselContentList.stream().map(this::toCarouselContentModel).collect(Collectors.toList());
    }

    private CarouselContentModel toCarouselContentModel(CarouselContent carouselContent){
        return CarouselContentModel.builder().carouselFeature(carouselContent.getCarouselFeature().toString()).imageUrl(carouselContent.getImageUrl()).link(carouselContent.getLink()).title(carouselContent.getTitle()).build();
    }

    private GenericResponseResource updateCarouselContent(CarouselContent carouselContent, CarouselContentModel carouselContentModel){
        carouselContent.setImageUrl(carouselContentModel.getImageUrl());
        carouselContent.setLink(carouselContentModel.getLink());
        carouselContent.setTitle(carouselContentModel.getTitle());
        carouselContent.setDescription(carouselContentModel.getDescription());
        carouselContent.setUser(carouselContentModel.getUser());
        carouselContent.setUserProfile(carouselContentModel.getUserProfile());
        carouselContentRepository.save(carouselContent);
        return GenericResponseResource.builder().message("Successfully updated carousel content").success(true).build();
    }

    private CarouselContent requestToCarouselContent(CarouselContentModel carouselContentModel){
        return CarouselContent.builder().description(carouselContentModel.getDescription()).user(carouselContentModel.getUser()).userProfile(carouselContentModel.getUserProfile()).carouselFeature(CarouselFeature.valueOf(carouselContentModel.getCarouselFeature())).imageUrl(carouselContentModel.getImageUrl()).link(carouselContentModel.getLink()).title(carouselContentModel.getTitle()).build();
    }

    private String validateCreateCarouselContent(CarouselContentModel carouselContentModel){
        if(carouselContentModel.getCarouselFeature() == null || carouselContentModel.getCarouselFeature().isEmpty()){
            return "Must contain carousel feature";
        }
        else if(!EnumUtils.isValidEnum(CarouselFeature.class, carouselContentModel.getCarouselFeature()) ){
            return "Must contain valid carousel feature: REVIEWS, NEWS, PODCASTS, EVENTS";
        }
        return null;
    }
}
