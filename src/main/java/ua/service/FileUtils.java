package ua.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {
    void write(MultipartFile multipartFile, String folderName, String fileName);

    void delete(String path);
}
