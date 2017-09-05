package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.filter.AttributeValueFilter;
import ua.entity.AttributeValue;

import java.util.List;

public interface AttributeValueService {
    void save(AttributeValue attributeValue);

    void delete(int id);

    AttributeValue findOne(int id);

    List<AttributeValue> findAll();

    Page<AttributeValue> findAll(int stringAttributeId, AttributeValueFilter filter, Pageable pageable);

    AttributeValue findOneWithAttribute(int id);

    boolean valueExist(AttributeValue value);

    List<AttributeValue> findAllByItem(int itemId);
}
