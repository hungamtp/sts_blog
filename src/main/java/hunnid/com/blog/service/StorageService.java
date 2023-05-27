package hunnid.com.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    boolean deleteFile(List<String> pathFile);

    List<String> uploadFiles(List<MultipartFile> files) throws IOException;
}
