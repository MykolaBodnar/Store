package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.ItemDto;
import ua.dto.filter.ItemFilterParam;
import ua.dto.ItemForm;
import ua.dto.ItemListDto;
import ua.dto.filter.ItemFilter;
import ua.entity.Item;

import java.util.List;

public interface ItemService {
    void save(ItemForm item);

    void delete(int item);

    Item findOne(int id);

    Page<ItemListDto> findAll(ItemFilter filter, Pageable pageable, int id);

    ItemForm findOneForm(int id);

    String getLongDescription(int id);

    void saveLongDescription(String description, int id);

    List<Item> findAll();

    Item findOne(String name);

    ItemDto findOneDto(int id);

    ItemFilterParam getFilterParam(int categoryId);

    Page<ItemListDto> findAll(String name, Pageable pageable);
}
