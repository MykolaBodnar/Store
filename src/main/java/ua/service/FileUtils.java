package ua.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {
    boolean write(MultipartFile multipartFile, String folderName, String fileName);
    boolean delete(String path);
}
