package hunnid.com.blog.converter;

import hunnid.com.blog.dto.response.AdminPostDTO;
import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationString;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PostConverter {
    
    public AdminPostDTO entityToDTO(Post post , String language){
        List<String> titles = post.getTranslatedStrings().stream()
                .filter(t -> Objects.equals(t.getLanguage().getName(), language) && t.getType().getType().equals(TranslationStringTypeEnum.POST_TITLE))
                .map(TranslationString::getTranslatedString)
                .toList();

        return AdminPostDTO.builder()
                .id(post.getId())
                .title(titles.size() > 0 ? titles.get(0) : "")
                .createAt(post.getCreatedAt())
                .viewCount(post.getViews().size())
                .hidden(post.isHidden())
                .build();
    }

}
