package hunnid.com.blog.service;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.PageDTO;
import hunnid.com.blog.dto.response.PostResponseDTO;
import hunnid.com.blog.dto.response.SavedPostResponseDTO;
import hunnid.com.blog.dto.response.TranslationStringTypeResponseDTO;
import hunnid.com.blog.entity.Post;
import hunnid.com.blog.entity.TranslationStringType;

import java.util.List;
import java.util.UUID;

public interface PostService {
    SavedPostResponseDTO save(CreatePostDTO request);

    SavedPostResponseDTO update(CreatePostDTO request);

    Boolean hideOrShowPost(HideOrShowRequestDTO request);
    
    Post findByIdOrElseThrow(UUID postId);
    
    PageDTO<PostResponseDTO> postsHomePage(int page , int size , String language, List<UUID> tagIds);

    List<TranslationStringTypeResponseDTO> getStringType();
}
