package hunnid.com.blog.dto.request;


import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreatePostDTO {
    private String coverImage;
    private Set<UUID> tagIds;
    private Map<UUID, List<PostContent>> contentMapByLanguageId;
}
