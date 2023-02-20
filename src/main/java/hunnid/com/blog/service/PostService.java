package hunnid.com.blog.service;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.response.PostResponseDTO;

public interface PostService {
    PostResponseDTO save(CreatePostDTO request);
}
