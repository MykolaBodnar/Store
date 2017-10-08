package ua.service.Implementation;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.dao.ItemDao;
import ua.dao.OrderDao;
import ua.dao.OrderItemDao;
import ua.dao.UserDao;
import ua.dto.DtoMapper;
import ua.dto.OrderDto;
import ua.dto.OrderItemDto;
import ua.entity.Item;
import ua.entity.Order;
import ua.entity.OrderItem;
import ua.service.OrderService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void save(Principal principal, String cookie) {
        Order order = new Order();
        order.setUser(userDao.findOne(Integer.parseInt(principal.getName())));
        orderDao.saveAndFlush(order);
        List<OrderItem> orderItems = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(cookie);
        for (String key : jsonObject.keySet()) {
            if (!"path".equals(key)) {
                JSONObject itemJson = jsonObject.getJSONObject(key);
                OrderItem orderItem = new OrderItem();
                orderItem.setCount(itemJson.getInt("count"));
                Item item = itemDao.findOne(Integer.parseInt(key));
                orderItem.setItem(item);
                orderItem.setOrderDetail(order);
                orderItems.add(orderItem);
            }
        }
        orderItemDao.save(orderItems);
    }

    @Override
    public List<OrderDto> findAllByUser(Principal principal) {
        List<Order> orders = orderDao.findAllByUser(Integer.parseInt(principal.getName()));
        List<OrderDto> orderDtos = DtoMapper.ordersToDto(orders);
        return orderDtos;
    }
}
