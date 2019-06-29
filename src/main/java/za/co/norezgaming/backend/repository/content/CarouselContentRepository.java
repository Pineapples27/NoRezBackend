package za.co.norezgaming.backend.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.norezgaming.backend.domain.content.CarouselContent;
import za.co.norezgaming.backend.domain.content.CarouselFeature;

import java.util.Optional;

public interface CarouselContentRepository extends JpaRepository<CarouselContent, Long> {
    Optional<CarouselContent> findByCarouselFeature(CarouselFeature carouselFeature);
}
