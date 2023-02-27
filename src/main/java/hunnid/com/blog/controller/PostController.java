package hunnid.com.blog.controller;

import hunnid.com.blog.dto.request.CreatePostDTO;
import hunnid.com.blog.dto.request.HideOrShowRequestDTO;
import hunnid.com.blog.dto.response.PostResponseDTO;
import hunnid.com.blog.entity.Post;
import hunnid.com.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @GetMapping
    @Operation(summary = "Get a foo by foo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "found the foo", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Foo not found", content = @Content)})
    public String demo() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody CreatePostDTO request) {
        return ResponseEntity.ok().body(postService.save(request));
    }
    
    @PatchMapping()
    public  ResponseEntity<Boolean> hideOtShowPost(@RequestBody HideOrShowRequestDTO request){
        return ResponseEntity.ok().body(postService.hideOrShowPost(request));
    }
}
