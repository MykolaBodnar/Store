package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.dao.*;
import ua.dto.*;
import ua.dto.filter.ItemFilter;
import ua.dto.filter.ItemFilterParam;
import ua.dto.filter.NameFilter;
import ua.dto.ProducerDto;
import ua.entity.*;
import ua.service.FileUtils;
import ua.service.ItemService;
import ua.service.specification.ItemSearchSpecification;
import ua.service.specification.ItemSpecification;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemImageDao itemImageDao;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private AttributeValueDao attributeValueDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private ProducerDao producerDao;

    @Override
    public void save(ItemForm itemForm) {
        Item item = DtoMapper.itemFormToItem(itemForm);
        if (item.getId() == 0) {
            itemDao.saveAndFlush(item);
            itemForm.setId(item.getId());
        }
        saveImage(item, itemForm.getFile());
        itemDao.save(item);

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
    public ItemDto findOneDto(int id) {
        Item item = itemDao.findOneWithCategory(id);
        String producer = itemDao.findOneWithProducer(id).getProducer().getName();
        List<AttributeValue> attributeValues = attributeValueDao.findAllByItem(id);
        List<ItemImage> itemImages = itemImageDao.findAllByItem(id);
        ItemDto itemDto = DtoMapper.getItemDto(item, producer, attributeValues, itemImages);
        return itemDto;
    }

    @Override
    public ItemFilterParam getFilterParam(int categoryId) {
        Category category = categoryDao.findOne(categoryId);
        List<Attribute> attributes = attributeDao.findAllWithValuesByCategoryId(categoryId);
        List<Producer> producers = producerDao.findAllByCategoryId(categoryId);
        ItemFilterParam itemFilterParam = DtoMapper.getItemFilterParam(category, attributes, producers);
        return itemFilterParam;
    }

    @Override
    public Page<ItemListDto> findAll(String name, Pageable pageable) {
        Page<Item> itemPage = itemDao.findAll(new ItemSearchSpecification(new NameFilter(name)), pageable);
        Page<ItemListDto> dtoPage = new PageImpl<>(DtoMapper.itemPageToListDto(itemPage)
                , pageable, itemPage.getTotalElements()
        );
        return dtoPage;
    }

    @Override
    public Page<ItemListDto> findAll(ItemFilter filter, Pageable pageable, int categoryId) {
        filter.setCategoryId(categoryId);
        Page<Item> itemPage = itemDao.findAll(new ItemSpecification(filter), pageable);
        Page<ItemListDto> dtoPage = new PageImpl<>(DtoMapper.itemPageToListDto(itemPage)
                , pageable, itemPage.getTotalElements()
        );
        return dtoPage;
    }

    @Override
    public ItemForm findOneForm(int id) {
        Item item = itemDao.findOneWithValues(id);
        Category category = itemDao.findOneWithCategory(id).getCategory();
        Producer producer = itemDao.findOneWithProducer(id).getProducer();
        ItemForm itemForm = DtoMapper.getItemForm(item, category, producer);
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


}
