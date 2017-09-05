package ua.service;

import org.springframework.web.multipart.MultipartFile;
import ua.entity.ItemImage;

import java.util.List;

public interface ItemImageService {
    void save(MultipartFile[] multipartFile, int itemId);

    void delete(int id);

    ItemImage findOne(int id);

    List<ItemImage> findAllByItemId(int itemId);

}
