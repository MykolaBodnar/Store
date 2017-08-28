package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.dao.ItemDao;
import ua.dao.ItemImageDao;
import ua.dto.ItemForm;
import ua.dto.filter.ItemFilter;
import ua.entity.Item;
import ua.service.FileUtils;
import ua.service.ItemService;
import ua.service.specification.ItemSpecification;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ItemImageDao itemImageDao;

    @Autowired
    FileUtils fileUtils;

    @Override
    public void save(ItemForm itemForm) {
        Item item = new Item();
        item.setId(itemForm.getId());
        item.setCategory(itemForm.getCategory());
        item.setId(itemForm.getId());
        item.setPrice(itemForm.getPrice());
        item.setName(itemForm.getName());
        item.setProducer(itemForm.getProducer());
        item.setAttributeValues(itemForm.getAttributeValues());
        item.setDescription(generateDescription(itemForm));
        if (item.getId() == 0) {
            itemDao.saveAndFlush(item);
        }
        saveImage(item, itemForm.getFile());
        itemDao.save(item);

    }

    private String generateDescription(ItemForm itemForm) {
        String description = itemForm.getAttributeValues().stream()
                .filter(sav -> sav.getAttribute().isForSelect())
                .map(sav -> "<b>" + sav.getAttribute().getName() + ": </b>" + sav.getValue())
                .collect(Collectors.joining(", "));

        return description;
    }

    private void saveImage(Item item, MultipartFile file) {
        String folderName = String.valueOf(item.getId());
        String fileName = "main.jpg";
        String imagePath = folderName + "/" + fileName;
        item.setImageUrl(imagePath);
        fileUtils.write(file, folderName, fileName);
    }

    @Override
    public void delete(int id) {
        itemDao.delete(id);
        fileUtils.delete(String.valueOf(id));
    }

    @Override
    public Item findOne(int id) {
        return itemDao.findOne(id);
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item findOne(String name) {
        return itemDao.findByName(name);
    }

    @Override
    public ItemForm findOneWithAll(int id) {
        Item item = itemDao.findOneWithValues(id);
        ItemForm itemForm = new ItemForm();
        itemForm.setAttributeValues(item.getAttributeValues());
        itemForm.setCategory(itemDao.findOneWithCategory(id).getCategory());
        itemForm.setProducer(itemDao.findOneWithProducer(id).getProducer());
        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setPrice(item.getPrice());
        itemForm.setImageUrl(item.getImageUrl());
        return itemForm;
    }

    @Override
    public String getLongDescription(int id) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(new File(
                    System.getProperty("catalina.home") + "/resources/" + id + "/longDescription.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file no found";
    }

    @Override
    public void saveLongDescription(String description, int id) {
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File(System.getProperty("catalina.home")
                    + "/resources/" + id + "/longDescription.html"), description);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Item> findAll(ItemFilter filter, Pageable pageable, int categoryId) {
        filter.setCategoryId(categoryId);
        return itemDao.findAll(new ItemSpecification(filter),pageable);
    }
}
