package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.dao.ItemDao;
import ua.dao.ItemImageDao;
import ua.entity.Item;
import ua.entity.ItemImage;
import ua.service.FileUtils;
import ua.service.ItemImageService;

import java.util.List;

@Service
@Transactional
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    ItemImageDao itemImageDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    FileUtils fileUtils;

    @Override
    public void save(MultipartFile[] multipartFiles, int itemId) {
        Item item = itemDao.findOne(itemId);
        for (int i = 0; i < multipartFiles.length; i++) {
            ItemImage itemImage = new ItemImage(item);
            itemImageDao.saveAndFlush(itemImage);
            String folderName = String.valueOf(itemId);
            String fileName = itemImage.getId() + ".jpg";
            String imagePath = folderName + "/" + fileName;
            if (fileUtils.write(multipartFiles[i], folderName, fileName)) {
                itemImage.setPath(imagePath);
                itemImageDao.save(itemImage);
            } else {
                itemImageDao.delete(itemImage);
            }
        }
    }

    @Override
    public void delete(int id) {
        fileUtils.delete(itemImageDao.findOne(id).getPath());
        itemImageDao.delete(id);
    }

    @Override
    public ItemImage findOne(int id) {
        return itemImageDao.findOne(id);
    }

    @Override
    public List<ItemImage> findAllByItemId(int itemId) {
        return itemImageDao.findAllByItemId(itemId);
    }

}
