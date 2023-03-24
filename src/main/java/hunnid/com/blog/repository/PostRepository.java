package hunnid.com.blog.repository;

import hunnid.com.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByHiddenFalseAndTags_IdIn(Pageable pageable ,List<UUID> tags);
}
