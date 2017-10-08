package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.entity.Order;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    @Query("select distinct o from User u join u.orders o left join fetch o.orderItems where u.id =:userId")
    List<Order> findAllByUser(@Param("userId") int userId);
}
