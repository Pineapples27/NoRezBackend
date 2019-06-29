package za.co.norezgaming.backend.controller.requestModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.norezgaming.backend.domain.account.MediaType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MediaModel {
    private String url;
    private MediaType mediaType;
}
