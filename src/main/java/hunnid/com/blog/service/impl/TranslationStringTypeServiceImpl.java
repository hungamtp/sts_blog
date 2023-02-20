package hunnid.com.blog.service.impl;

import hunnid.com.blog.constraint.EntityNameConstraint;
import hunnid.com.blog.constraint.ErrorMessageConstraint;
import hunnid.com.blog.entity.TranslationStringType;
import hunnid.com.blog.exceptionHandler.exception.NotFoundException;
import hunnid.com.blog.repository.TranslationStringTypeRepository;
import hunnid.com.blog.service.TranslationStringTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TranslationStringTypeServiceImpl implements TranslationStringTypeService {
    private final TranslationStringTypeRepository translationStringTypeRepository;

    @Override
    public TranslationStringType findByIdOrElseThrow(UUID id) {
        return translationStringTypeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityNameConstraint.TRANSLATION_STRING_TYPE, String.format(ErrorMessageConstraint.NOT_FOUND, id))
        );
    }
}
