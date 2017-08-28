package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.filter.NameFilter;
import ua.entity.Attribute;
import ua.entity.Category;

import java.util.List;

public interface AttributeService {
    void save(Attribute attribute);
    void delete(int id);
    Attribute findOne(int id);
    List<Attribute> findAll();

    List<Attribute> findAllWithOutLoaded(List<Attribute> loadedAttributes);

    List<Attribute> findAllWithValuesByCategoryId(int categoryId);

    Page<Attribute> findAll(NameFilter filter, Pageable pageable);

    Page<Attribute> findAllWithOutLoaded(Category category, NameFilter filter, Pageable pageable);

    Attribute findOne(String name);
}
