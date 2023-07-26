package hunnid.com.blog.controller;

import hunnid.com.blog.cusAnnotation.Log;
import hunnid.com.blog.dto.response.LanguageDTO;
import hunnid.com.blog.entity.Language;
import hunnid.com.blog.repository.LanguageRepository;
import hunnid.com.blog.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getLanguages() {
        return ResponseEntity.ok().body(languageService.getLanguages());
    }
    
    @GetMapping("/aop")
    public void AOP() {
        languageService.testAOP();
        return ;
    }

}
