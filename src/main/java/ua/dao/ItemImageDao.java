package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.ItemImage;

import java.util.List;

public interface ItemImageDao  extends JpaRepository<ItemImage,Integer>{
    @Query("select ii from ItemImage ii where ii.item.id =:itemId")
    List<ItemImage> findAllByItemId(@Param("itemId") int itemId);
}
