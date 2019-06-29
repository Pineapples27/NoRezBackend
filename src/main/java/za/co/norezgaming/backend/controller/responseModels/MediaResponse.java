package za.co.norezgaming.backend.controller.responseModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.norezgaming.backend.domain.account.MediaType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MediaResponse {
    private String url;
    private MediaType mediaType;
}
