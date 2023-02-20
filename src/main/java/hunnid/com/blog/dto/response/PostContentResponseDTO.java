package hunnid.com.blog.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Builder
@Getter
@Setter
public class PostContentResponseDTO {
    @NotBlank
    private String translatedString;
    @NotBlank
    private UUID contentTypeId;
}
