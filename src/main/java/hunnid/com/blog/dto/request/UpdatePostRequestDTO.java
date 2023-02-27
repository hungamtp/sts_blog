package hunnid.com.blog.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UpdatePostRequestDTO {
    private String coverImage;
    private Set<UUID> tagIds;
    private Boolean isUpdateContent;
    private Map<UUID, List<PostContent>> contentMapByLanguageId;
}
