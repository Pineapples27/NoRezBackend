package za.co.norezgaming.backend.controller.requestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.norezgaming.backend.domain.account.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAccountModel {
    private String gamerTag;
    private String password;

    private Role role;
}
