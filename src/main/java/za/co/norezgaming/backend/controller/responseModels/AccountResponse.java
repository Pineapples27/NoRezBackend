package za.co.norezgaming.backend.controller.responseModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.norezgaming.backend.domain.account.Media;
import za.co.norezgaming.backend.domain.account.Role;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountResponse {
    private UUID accountUUID;

    private String gamerTag;
    private Role role;

    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private int pointsAccumulated;

    private List<MediaResponse> media;
}
