package hunnid.com.blog.service;

import hunnid.com.blog.entity.TranslationStringType;

import java.util.UUID;

public interface TranslationStringTypeService {
    TranslationStringType findByIdOrElseThrow(UUID id);
}
