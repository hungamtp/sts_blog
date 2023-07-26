package hunnid.com.blog.service;

import hunnid.com.blog.cusAnnotation.Log;
import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.entity.Language;

import java.util.List;
import java.util.UUID;

public interface LanguageService {
    List<LanguageDTO> getLanguages();
    Language findByIdOrThrowException(UUID id);
    
    @Log
    void testAOP();
}
