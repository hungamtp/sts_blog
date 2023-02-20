package hunnid.com.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreatePostDTO {
    private String coverImage;
    private Map<UUID , PostContent> languageMapContent ;
    public class PostContent{
        private String content;
        private String title;
    }
}
