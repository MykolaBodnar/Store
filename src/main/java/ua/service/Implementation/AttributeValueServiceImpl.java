package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.AttributeDao;
import ua.dao.AttributeValueDao;
import ua.dto.filter.AttributeValueFilter;
import ua.entity.AttributeValue;
import ua.service.AttributeValueService;
import ua.service.specification.AttributeValueSpecification;

import java.util.List;

@Service
@Transactional
public class AttributeValueServiceImpl implements AttributeValueService {

    @Autowired
    private AttributeValueDao attributeValueDao;

    @Override
    public void save(AttributeValue attributeValue) {
        attributeValueDao.save(attributeValue);
    }

    @Override
    public void delete(int stringAttributeValue) {
        attributeValueDao.delete(stringAttributeValue);
    }

    @Override
    public AttributeValue findOne(int id) {
        return attributeValueDao.findOne(id);
    }

    @Override
    public List<AttributeValue> findAll() {
        return attributeValueDao.findAll();
    }

    @Override
    public Page<AttributeValue> findAll(int stringAttributeId, AttributeValueFilter filter, Pageable pageable) {
        return attributeValueDao.findAll(new AttributeValueSpecification(stringAttributeId, filter), pageable);
    }

    @Override
    public AttributeValue findOneWithAttribute(int id) {
        return attributeValueDao.findOneWithAttribute(id);
    }

    @Override
    public boolean valueExist(AttributeValue attributeValue) {
        return attributeValueDao.valueExist(attributeValue.getValue(), attributeValue.getAttribute().getId());
    }

    @Override
    public List<AttributeValue> findAllByItem(int itemId) {
        return attributeValueDao.findAllByItem(itemId);
    }
}
