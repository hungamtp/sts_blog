package hunnid.com.blog.controller;

import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.entity.Language;
import hunnid.com.blog.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageRepository languageRepository;

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getLanguages() {
        return ResponseEntity.ok().body(languageRepository.findAll()
            .stream()
            .map(language ->
                LanguageDTO.builder()
                    .id(language.getId())
                    .language(language.getName())
                    .build())
            .collect(Collectors.toList()));
    }
}
