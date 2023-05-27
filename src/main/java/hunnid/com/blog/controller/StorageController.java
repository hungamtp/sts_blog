package hunnid.com.blog.controller;

import hunnid.com.blog.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StorageController {
    
    private final StorageService s3Service;

    @PostMapping("/files")
    public String uploadFiles(@RequestParam List<MultipartFile> files) throws IOException {
        s3Service.uploadFiles(files);
        return "";
    }
}
