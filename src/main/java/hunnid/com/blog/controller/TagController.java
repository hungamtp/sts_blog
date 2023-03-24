package hunnid.com.blog.controller;

import hunnid.com.blog.dto.response.TagDTO;
import hunnid.com.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getTags(){
        return ResponseEntity.ok().body(tagService.getTags());
    }
}
