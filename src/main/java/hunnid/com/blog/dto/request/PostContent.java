package hunnid.com.blog.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Builder
public class PostContent{
    @NotBlank
    private String translatedString;
    @NotBlank
    private UUID contentTypeId;
    
    private UUID languageId;

}
