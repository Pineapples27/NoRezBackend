package za.co.norezgaming.backend.domain.account;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID accountUUID;

    private String gamerTag;
    private String password;
    private Role role;

    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private int pointsAccumulated;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Media> media;
}
