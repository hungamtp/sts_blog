package hunnid.com.blog.service.impl;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.PostContent;
import hunnid.com.blog.dto.response.PostContentResponseDTO;
import hunnid.com.blog.dto.response.PostResponseDTO;
import hunnid.com.blog.dto.response.TagDTO;
import hunnid.com.blog.entity.Language;
import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationString;
import hunnid.com.blog.entity.TranslationStringType;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import hunnid.com.blog.repository.LanguageRepository;
import hunnid.com.blog.repository.PostRepository;
import hunnid.com.blog.service.LanguageService;
import hunnid.com.blog.service.PostService;
import hunnid.com.blog.service.TagService;
import hunnid.com.blog.service.TranslationStringTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LanguageService languageService;
    private final TranslationStringTypeService translationStringTypeService;
    private final TagService tagService;

    @Override
    public PostResponseDTO save(CreatePostDTO request) {
        Post newPost = Post.builder()
                .coverImage(request.getCoverImage())
                .hidden(true)
                .build();

        Set<TranslationString> translationStrings = new HashSet<>();
        newPost.setTranslatedStrings(translationStrings);
        request.getContentMapByLanguageId().forEach((key, value) -> {
            Language language = languageService.findByIdOrThrowException(key);
            value.forEach((translationString) -> {
                TranslationStringType type = translationStringTypeService.findByIdOrElseThrow(translationString.getContentTypeId());
                translationStrings.add(TranslationString.builder()
                        .language(language)
                        .translatedString(translationString.getTranslatedString())
                        .type(type)
                        .build());
            });
        });
        newPost.setTags(tagService.findByIdInOrElseThrow(request.getTagIds()));
        postRepository.save(newPost);

        return PostResponseDTO.builder()
                .id(newPost.getId())
                .coverImage(newPost.getCoverImage())
                .titles(newPost.getTranslatedStrings().stream()
                        .filter(translationString -> Objects.equals(translationString.getType().getType(), TranslationStringTypeEnum.POST_TITLE))
                        .collect(Collectors.toMap(t -> t.getLanguage().getName(), TranslationString::getTranslatedString)))
                .contents(newPost.getTranslatedStrings().stream()
                        .filter(translationString -> Objects.equals(translationString.getType().getType(), TranslationStringTypeEnum.POST_CONTENT))
                        .collect(Collectors.toMap(t -> t.getLanguage().getName(), TranslationString::getTranslatedString)))
                .createdAt(newPost.getCreatedAt())
                .hidden(newPost.isHidden())
                .tags(newPost.getTags().stream().map(tag -> TagDTO.builder().id(tag.getId()).tag(tag.getTag()).build()).collect(toSet()))
                .build();
    }
}
