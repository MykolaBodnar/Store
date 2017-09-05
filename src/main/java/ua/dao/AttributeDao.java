package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.Attribute;

import java.util.List;

@Repository
public interface AttributeDao extends JpaRepository<Attribute, Integer>, JpaSpecificationExecutor<Attribute> {

    @Query("select distinct a from Category c join c.attributes a left join fetch a.attributeValues " +
            "where c.id =:categoryId")
    List<Attribute> findAllWithValuesByCategoryId(@Param("categoryId") int categoryId);

    Attribute findByName(String name);
}
