package hunnid.com.blog.dto.response;

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
}
