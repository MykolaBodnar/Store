package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.Producer;

import java.util.List;

public interface ProducerDao extends JpaRepository<Producer,Integer>, JpaSpecificationExecutor<Producer> {
    @Query("select p from Producer p left join fetch p.items where p.id =:id")
    Producer findOneWithItems(@Param("id") int id);

    @Query("select distinct p from Category c join c.items i join i.producer p where c.id =:categoryId")
    List<Producer> findAllByCategoryId(@Param("categoryId") int categoryId);

    Producer findByName(String name);
}
