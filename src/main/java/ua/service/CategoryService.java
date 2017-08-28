package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.filter.NameFilter;
import ua.entity.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    void delete(int id);
    Category findOne(int id);
    List<Category> findAll();

    void addAttribute(int id, int attributeId);

    void deleteAttribute(int id, int attributeId);

    Category findOneWithAttribute(int id);

    Category findOneWithItems(int id);

    Page<Category> findAll(NameFilter filter, Pageable pageable);

    Category findOne(String name);
}
