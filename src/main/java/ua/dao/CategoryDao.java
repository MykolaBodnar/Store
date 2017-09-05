package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    @Query("select c from Category c left join fetch c.attributes where c.id =:id")
    Category findOneWithAttribute(@Param("id") int id);

    @Query("select c from Category c left join fetch c.items where c.id =:id")
    Category findOneWithItems(@Param("id") int id);

    Category findByName(String name);
}

