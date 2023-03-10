package hunnid.com.blog.service.impl;

import hunnid.com.blog.constraint.EntityNameConstraint;
import hunnid.com.blog.constraint.ErrorMessageConstraint;
import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.PageDTO;
import hunnid.com.blog.dto.response.PostResponseDTO;
import hunnid.com.blog.dto.response.SavedPostResponseDTO;
import hunnid.com.blog.dto.response.TagDTO;
import hunnid.com.blog.entity.*;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import hunnid.com.blog.exceptionHandler.exception.NotFoundException;
import hunnid.com.blog.repository.PostRepository;
import hunnid.com.blog.service.LanguageService;
import hunnid.com.blog.service.PostService;
import hunnid.com.blog.service.TagService;
import hunnid.com.blog.service.TranslationStringTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public SavedPostResponseDTO save(CreatePostDTO request) {
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

        return SavedPostResponseDTO.builder()
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

    @Override
    public SavedPostResponseDTO update(CreatePostDTO request) {
        return null;
    }

    @Override
    public Boolean hideOrShowPost(HideOrShowRequestDTO request) {
        Post post = findByIdOrElseThrow(request.getPostId());
        post.setHidden(request.getHidden());
        postRepository.save(post);
        return Boolean.TRUE;
    }

    @Override
    public Post findByIdOrElseThrow(UUID postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(EntityNameConstraint.POST, String.format(ErrorMessageConstraint.NOT_FOUND, postId))
        );
    }

    @Override
    public PageDTO<PostResponseDTO> postsHomePage(int page, int size, UUID languageId) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Post_.CREATED_AT).descending());
        Page<Post> posts = postRepository.findAllByHiddenFalse(pageable);

        return PageDTO.<PostResponseDTO>builder()
                .data(posts.get().map(p -> PostResponseDTO.entityToDTO(p, languageId)).collect(toList()))
                .page(page)
                .size(size)
                .totalResults((int) posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .actualResult(posts.getNumberOfElements())
                .build();
    }
}
