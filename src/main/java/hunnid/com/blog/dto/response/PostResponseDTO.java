package hunnid.com.blog.dto.response;

import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationString;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class PostResponseDTO {
    private UUID id;
    private String title;
    private String content;
    private String coverImage;
    private LocalDateTime createdDate;
    private int viewCount;
    private List<TagDTO> tags;

    public static PostResponseDTO entityToDTO(Post post, UUID languageId) {
        List<String> titles = post.getTranslatedStrings().stream()
                .filter(t -> Objects.equals(t.getLanguage().getId(), languageId) && t.getType().getType().equals(TranslationStringTypeEnum.POST_TITLE))
                .map(TranslationString::getTranslatedString)
                .toList();
        List<String> contents = post.getTranslatedStrings().stream()
                .filter(t -> Objects.equals(t.getLanguage().getId(), languageId) && t.getType().getType().equals(TranslationStringTypeEnum.POST_CONTENT))
                .map(TranslationString::getTranslatedString)
                .toList();
        String title = null;
        String content = null;
        
        if (titles.size() > 0) {
            title = titles.get(0);
        }

        if (contents.size() > 0) {
            content = contents.get(0);
        }
        
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(title)
                .content(content)
                .createdDate(post.getCreatedAt())
                .viewCount(post.getViews().size())
                .coverImage(post.getCoverImage())
                .tags(post.getTags().stream().map(TagDTO::entityToDTO).collect(Collectors.toList()))
                .build();
    }

}
