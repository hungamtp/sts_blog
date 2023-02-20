package hunnid.com.blog.service;

import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.dto.response.TagDTO;
import hunnid.com.blog.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<TagDTO> getTags();
    Tag findByIdOrElseThrow(UUID id);
    Set<Tag> findByIdInOrElseThrow(Set<UUID> id);
}
