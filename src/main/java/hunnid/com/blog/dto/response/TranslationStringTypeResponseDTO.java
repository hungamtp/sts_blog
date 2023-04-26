package hunnid.com.blog.dto.response;

import hunnid.com.blog.entity.TranslationStringType;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TranslationStringTypeResponseDTO {
    private UUID id;
    private TranslationStringTypeEnum type;
    
    public static TranslationStringTypeResponseDTO entityToDTO(TranslationStringType entity){
        return TranslationStringTypeResponseDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }
}
