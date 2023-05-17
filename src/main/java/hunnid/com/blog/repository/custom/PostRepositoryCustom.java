package hunnid.com.blog.repository.custom;

import hunnid.com.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostRepositoryCustom {
    List<Post> search(List<UUID> tags, String searchKeyWord, int page , int size);
}
