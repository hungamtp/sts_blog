package hunnid.com.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HideOrShowRequestDTO {
    private UUID postId;
    private Boolean hidden;
}
