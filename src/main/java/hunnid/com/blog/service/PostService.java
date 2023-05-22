package hunnid.com.blog.service;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.*;
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

    PageDTO<AdminPostDTO> getPostsAdminPanel(int page , int size , String language);

    PostDetailDTO getPostDetail(UUID postId, String language);

    PageDTO<PostResponseDTO> search(int page , int size , String language,String keyword);
}
