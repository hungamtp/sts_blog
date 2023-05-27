package hunnid.com.blog.dto.response;

import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.Tag;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import hunnid.com.blog.util.StringUtils;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDetailDTO {
    private UUID id;
    private String title;
    private String content;
    private String coverImage;
    private LocalDate date;
    private Integer viewCount;
    private List<String> tags;

    public static PostDetailDTO entityToDTO(Post post, String language) {
        String title = null;
        String content = null;
        if (post.getTranslatedStrings().stream()
                .anyMatch(t -> t.getType().getType().equals(TranslationStringTypeEnum.POST_TITLE) && t.getLanguage().getName().equals(language))) {
            title = post.getTranslatedStrings().stream()
                    .filter(t -> t.getType().getType().equals(TranslationStringTypeEnum.POST_TITLE)
                            && t.getLanguage().getName().equals(language)).collect(Collectors.toList()).get(0).getTranslatedString();
        }

        if (post.getTranslatedStrings().stream()
                .anyMatch(t -> t.getType().getType().equals(TranslationStringTypeEnum.POST_CONTENT) && t.getLanguage().getName().equals(language))) {
            content = post.getTranslatedStrings().stream()
                    .filter(t -> t.getType().getType().equals(TranslationStringTypeEnum.POST_CONTENT)
                            && t.getLanguage().getName().equals(language)).collect(Collectors.toList()).get(0).getTranslatedString();
        }
        return PostDetailDTO.builder()
                .id(post.getId())
                .title(title)
                .content(StringUtils.encode(content))
                .coverImage(post.getCoverImage())
                .date(post.getCreatedAt().toLocalDate())
                .viewCount(post.getViews().size())
                .tags(post.getTags().stream().map(Tag::getTag).collect(Collectors.toList()))
                .build();
    }

}
