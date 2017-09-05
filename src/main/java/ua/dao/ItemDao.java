package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

    @Query("select i from Item i left join fetch i.attributeValues where i.id =:id")
    Item findOneWithValues(@Param("id") int id);

    @Query("select i from Item i left join fetch i.category where i.id =:id")
    Item findOneWithCategory(@Param("id") int id);

    @Query("select i from Item i left join fetch i.producer where i.id =:id")
    Item findOneWithProducer(@Param("id") int id);

    Item findByName(String name);
}
