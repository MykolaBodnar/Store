package ua.service.Implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.service.FileUtils;

import java.io.File;
import java.io.IOException;

@Service
public class FileUtilsImpl implements FileUtils {
    @Override
    public boolean write(MultipartFile multipartFile, String folderName, String fileName) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String path = System.getProperty("catalina.home") + "/resources/" + folderName;
            File pathToFolder = new File(path);
            if (!pathToFolder.exists()) {
                pathToFolder.mkdirs();
            }
            File pathToFile = new File(pathToFolder, fileName);
            try {
                multipartFile.transferTo(pathToFile);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(String path) {
        File file = new File(System.getProperty("catalina.home") + "/resources/" + path);
        if (file.isDirectory()) {
            for (File element : file.listFiles()) {
                element.delete();
            }
        }
        return file.delete();
    }
}
