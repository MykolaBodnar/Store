package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.CategoryDao;
import ua.dao.ItemDao;
import ua.dao.AttributeDao;
import ua.dto.filter.NameFilter;
import ua.entity.Category;
import ua.entity.Item;
import ua.service.CategoryService;
import ua.service.FileUtils;
import ua.service.specification.CategorySpecification;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ItemDao itemDao;


    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    FileUtils fileUtils;

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(int id) {
        for(Item item:categoryDao.findOneWithItems(id).getItems()){
            fileUtils.delete(String.valueOf(item.getId()));
        }
        categoryDao.delete(id);
    }

    @Override
    public Category findOne(int id) {
        return categoryDao.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void addAttribute(int id, int attributeId) {
        Category category = categoryDao.findOneWithAttribute(id);
        category.getAttributes().add(attributeDao.findOne(attributeId));
        categoryDao.save(category);
    }

    @Override
    public void deleteAttribute(int id, int attributeId) {
        Category category = categoryDao.findOneWithAttribute(id);
        category.getAttributes().removeIf(da-> da.getId()== attributeId);
        categoryDao.save(category);
    }

    @Override
    public Category findOneWithAttribute(int id) {
        return categoryDao.findOneWithAttribute(id);
    }

    @Override
    public Category findOneWithItems(int id) {
        return categoryDao.findOneWithItems(id);
    }

    @Override
    public Page<Category> findAll(NameFilter filter, Pageable pageable) {
        return categoryDao.findAll(new CategorySpecification(filter),pageable);
    }

    @Override
    public Category findOne(String name) {
        return categoryDao.findByName(name);
    }
}
