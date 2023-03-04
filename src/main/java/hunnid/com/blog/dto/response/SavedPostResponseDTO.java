package hunnid.com.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class SavedPostResponseDTO {
    private UUID id;
    private String coverImage;
    private LocalDateTime createdAt;
    private Boolean hidden;
    private Map<String , String> titles;
    private Map<String , String> contents;
    private Set<TagDTO> tags;
}
