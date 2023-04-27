package hunnid.com.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class AdminPostDTO {
    private UUID id;
    private String title;
    private LocalDateTime createAt;
    private boolean hidden;
    private Integer viewCount;
    
}
