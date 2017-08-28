package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.ItemForm;
import ua.dto.filter.ItemFilter;
import ua.entity.Item;

import java.util.List;

public interface ItemService {
    void save(ItemForm item);
    void delete(int item);
    Item findOne(int id);
    Page<Item> findAll(ItemFilter filter, Pageable pageable, int id);

    ItemForm findOneWithAll(int id);

    String getLongDescription(int id);

    void saveLongDescription(String description, int id);

    List<Item> findAll();

    Item findOne(String name);
}
