package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.AttributeValue;

import java.util.List;

@Repository
public interface AttributeValueDao extends JpaRepository<AttributeValue,Integer>, JpaSpecificationExecutor<AttributeValue> {
    List<AttributeValue> findAllByAttributeId(@Param("id") int id);

    @Query("select  av from AttributeValue av left join fetch av.attribute where av.id =:id")
    AttributeValue findOneWithAttribute(@Param("id") int id);

    @Query("select CASE WHEN COUNT(av) > 0 THEN true ELSE false END  from  AttributeValue av inner join  av.attribute a " +
            "where av.value =:attributeValue and a.id =:attributeId")
    boolean valueExist(@Param("attributeValue") String value, @Param("attributeId") int id);

    /*
    boolean valueExist(String value);

    boolean attributeByNameExistsInCategory(@Param("name") String name, @Param("categoryId") int categoryId);*/
}
