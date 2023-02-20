package hunnid.com.blog.service.impl;

import hunnid.com.blog.constraint.EntityNameConstraint;
import hunnid.com.blog.constraint.ErrorMessageConstraint;
import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.entity.Language;
import hunnid.com.blog.exceptionHandler.exception.NotFoundException;
import hunnid.com.blog.repository.LanguageRepository;
import hunnid.com.blog.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageDTO> getLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(language -> LanguageDTO
                        .builder()
                        .id(language.getId().toString())
                        .language(language.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Language findByIdOrThrowException(UUID id) {
        return languageRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityNameConstraint.LANGUAGE, String.format(ErrorMessageConstraint.NOT_FOUND, id)));

    }
}
