package hunnid.com.blog.dto.response;

import hunnid.com.blog.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TagDTO {
    private UUID id;
    private String tag;
    public static TagDTO entityToDTO(Tag tag){
        return TagDTO.builder()
                .id(tag.getId())
                .tag(tag.getTag())
                .build();
    }
}
