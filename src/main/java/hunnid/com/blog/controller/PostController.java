package hunnid.com.blog.controller;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.SavedPostResponseDTO;
import hunnid.com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {
    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(PostController.class);
    private final PostService postService;
    

    @PostMapping
    public ResponseEntity<SavedPostResponseDTO> createPost(@Valid @RequestBody CreatePostDTO request) {
        return ResponseEntity.ok().body(postService.save(request));
    }
    
    @PatchMapping()
    public  ResponseEntity<Boolean> hideOtShowPost(@RequestBody HideOrShowRequestDTO request){
        return ResponseEntity.ok().body(postService.hideOrShowPost(request));
    }

    @GetMapping
    public ResponseEntity getPosts(@RequestParam Integer size,
                                   @RequestParam Integer page,
                                   @RequestParam String language,
                                   @RequestParam(required = false) List<UUID> tagIds) {
        return ResponseEntity.ok().body(postService.postsHomePage(page, size, language,tagIds));
    }

    @GetMapping("/{postId}")
    public ResponseEntity getPostDetail(@PathVariable UUID postId, @RequestParam String language) {
        return ResponseEntity.ok().body(postService.getPostDetail(postId, language));
    }

    @GetMapping("/content-type")
    public ResponseEntity getContentType(){
        return ResponseEntity.ok().body(postService.getStringType());
    }
    
    @GetMapping("/manage")
    public ResponseEntity getPostInAdminPanel(@RequestParam Integer page,
                                              @RequestParam Integer size,
                                              @RequestParam String language) {
        return ResponseEntity.ok().body(postService.getPostsAdminPanel(page, size, language));
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam Integer page,
                                 @RequestParam Integer size,
                                 @RequestParam String language,
                                 @RequestParam String keyword) {
        return ResponseEntity.ok().body(postService.search(page, size, language, keyword));
    }
}
