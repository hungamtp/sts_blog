package hunnid.com.blog.service.impl;

import hunnid.com.blog.constraint.EntityNameConstraint;
import hunnid.com.blog.constraint.ErrorMessageConstraint;
import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.dto.response.TagDTO;
import hunnid.com.blog.entity.Tag;
import hunnid.com.blog.exceptionHandler.exception.NotFoundException;
import hunnid.com.blog.repository.TagRepository;
import hunnid.com.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public List<TagDTO> getTags() {
        return tagRepository.findAll()
                .stream()
                .map(TagDTO::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Tag findByIdOrElseThrow(UUID id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityNameConstraint.TAG, String.format(ErrorMessageConstraint.NOT_FOUND, id))
        );
    }

    @Override
    public Set<Tag> findByIdInOrElseThrow(Set<UUID> ids) {
        Set<Tag> tags = new HashSet<>();
        ids.stream().forEach((id) ->{
            tags.add(findByIdOrElseThrow(id));
        });
        return tags;
    }
}
