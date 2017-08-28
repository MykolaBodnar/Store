package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.AttributeDao;
import ua.dao.AttributeValueDao;
import ua.dto.filter.NameFilter;
import ua.entity.Category;
import ua.entity.Attribute;
import ua.service.AttributeService;
import ua.service.specification.AttributeExcludeCategorySpecification;
import ua.service.specification.AttributeSpecification;

import java.util.List;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    AttributeDao attributeDao;

    @Autowired
    AttributeValueDao attributeValueDao;

    @Override
    public void save(Attribute attribute) {
        attributeDao.save(attribute);
    }

    @Override
    public void delete(int id) {
        attributeDao.delete(id);
    }

    @Override
    public Attribute findOne(int id) {
        return attributeDao.findOne(id);
    }

    @Override
    public List<Attribute> findAll() {
        return attributeDao.findAll();
    }

    @Override
    public List<Attribute> findAllWithOutLoaded(List<Attribute> loadedAttributes) {
        List<Attribute> attributes = attributeDao.findAll();
        attributes.removeAll(loadedAttributes);
        return attributes;
    }

    @Override
    public List<Attribute> findAllWithValuesByCategoryId(int categoryId) {
        return attributeDao.findAllWithValuesByCategoryId(categoryId);
    }

    @Override
    public Page<Attribute> findAll(NameFilter filter, Pageable pageable) {
        return attributeDao.findAll(new AttributeSpecification(filter),pageable);
    }

    @Override
    public Page<Attribute> findAllWithOutLoaded(Category category, NameFilter filter, Pageable pageable) {
        return attributeDao.findAll(new AttributeExcludeCategorySpecification(filter,category),pageable);
    }

    @Override
    public Attribute findOne(String name) {
        return attributeDao.findByName(name);
    }

}
