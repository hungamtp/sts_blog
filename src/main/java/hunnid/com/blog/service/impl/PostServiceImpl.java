package hunnid.com.blog.service.impl;

import hunnid.com.blog.constraint.EntityNameConstraint;
import hunnid.com.blog.constraint.ErrorMessageConstraint;
import hunnid.com.blog.converter.PostConverter;
import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.*;
import hunnid.com.blog.entity.*;
import hunnid.com.blog.enums.TranslationStringTypeEnum;
import hunnid.com.blog.exceptionHandler.exception.NotFoundException;
import hunnid.com.blog.repository.PostRepository;
import hunnid.com.blog.repository.TranslationStringTypeRepository;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
    private final EntityManager entityManager;
    private final TranslationStringTypeRepository translationStringTypeRepository;
    private final PostConverter postConverter;

    @Override
    public SavedPostResponseDTO save(CreatePostDTO request) {
        Post newPost = Post.builder()
                .coverImage(request.getCoverImage())
                .hidden(true)
                .build();
        
        request.mapContentByLanguageId();

        Set<TranslationString> translationStrings = new HashSet<>();
        newPost.setTranslatedStrings(translationStrings);
        request.getContentMapByLanguageId().forEach((key, value) -> {
            value.forEach((translationString) -> {
                translationStrings.add(TranslationString.builder()
                        .language(entityManager.getReference(Language.class, key))
                        .translatedString(translationString.getTranslatedString())
                        .type(entityManager.getReference(TranslationStringType.class, translationString.getContentTypeId()))
                        .build());
            });
        });
        List<Tag> tags = new ArrayList<>();
        request.getTagIds().forEach((value) -> {
            tags.add(entityManager.getReference(Tag.class , value));
        });
        newPost.setTags(tags.stream().collect(toSet()));
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
    public PageDTO<PostResponseDTO> postsHomePage(int page, int size, String language, List<UUID> tagIds) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Post_.CREATED_AT).descending());

        Page<Post> posts = postRepository.findDistinctByHiddenFalseAndTags_IdIn(pageable,Objects.isNull(tagIds) ? tagService.getTags().stream().map(TagDTO::getId).collect(toList()) : tagIds);

        return PageDTO.<PostResponseDTO>builder()
                .data(posts.get().map(p -> PostResponseDTO.entityToDTO(p, language)).collect(toList()))
                .page(page)
                .size(size)
                .totalResults((int) posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .actualResult(posts.getNumberOfElements())
                .build();
    }

    @Override
    public List<TranslationStringTypeResponseDTO> getStringType() {
        return translationStringTypeRepository.findAll()
                .stream()
                .map(TranslationStringTypeResponseDTO::entityToDTO)
                .collect(toList());
    }

    @Override
    public PageDTO<AdminPostDTO> getPostsAdminPanel(int page, int size, String language) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Post_.CREATED_AT).descending());

        Page<Post> posts = postRepository.findAll(pageable);

        return PageDTO.<AdminPostDTO>builder()
                .data(posts.get().map(p -> postConverter.entityToDTO(p, language)).collect(toList()))
                .page(page)
                .size(size)
                .totalResults((int) posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .actualResult(posts.getNumberOfElements())
                .build();
    }

    @Override
    public PostDetailDTO getPostDetail(UUID postId, String language) {
        Post post = postRepository.findByHiddenIsFalseAndId(postId).orElseThrow(
                () -> new EntityNotFoundException()
        );

        return PostDetailDTO.entityToDTO(post, language);
    }
}
