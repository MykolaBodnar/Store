package ua.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.entity.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {
}
