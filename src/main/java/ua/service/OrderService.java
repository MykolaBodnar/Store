package ua.service;

import ua.dto.OrderDto;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void save(Principal principal, String cookies);

    List<OrderDto> findAllByUser(Principal userId);
}
