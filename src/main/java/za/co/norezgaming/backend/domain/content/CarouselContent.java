package za.co.norezgaming.backend.domain.content;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "carouselContent")
public class CarouselContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CarouselFeature carouselFeature;
    private String imageUrl;
    private String title;
    private String link;
    private String description;
    private String user;
    private String userProfile;
}
