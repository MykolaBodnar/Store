package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.dto.filter.NameFilter;
import ua.entity.Producer;

import java.util.List;

public interface ProducerService {
    void save(Producer producer);
    void delete(int id);
    Producer findOne(int id);
    List<Producer> findAll();
    List<Producer> findAllByCategoryId(int id);

    Page<Producer> findAll(NameFilter filter, Pageable pageable);

    Producer findOne(String name);
}
