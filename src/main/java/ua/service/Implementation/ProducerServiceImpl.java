package ua.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.ItemDao;
import ua.dao.ProducerDao;
import ua.dto.filter.NameFilter;
import ua.entity.Producer;
import ua.service.ProducerService;
import ua.service.specification.ProducerSpecification;

import java.util.List;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerDao producerDao;

    @Autowired
    ItemDao itemDao;

    @Override
    public void save(Producer producer) {
        producerDao.save(producer);
    }

    @Override
    public void delete(int id) {
        producerDao.delete(id);
    }

    @Override
    public Producer findOne(int id) {
        return producerDao.findOne(id);
    }

    @Override
    public List<Producer> findAll() {
        return producerDao.findAll();
    }

    @Override
    public List<Producer> findAllByCategoryId(int id) {
        return producerDao.findAllByCategoryId(id);
    }

    @Override
    public Page<Producer> findAll(NameFilter filter, Pageable pageable) {
        return producerDao.findAll(new ProducerSpecification(filter),pageable);
    }

    @Override
    public Producer findOne(String name) {
        return producerDao.findByName(name);
    }
}
