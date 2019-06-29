package za.co.norezgaming.backend.controller.requestModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarouselContentModel {
    private String carouselFeature;
    private String imageUrl;
    private String title;
    private String link;
    private String description;
    private String user;
    private String userProfile;
}
