package ua.service;


import ua.entity.Order;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface OrderService {
    List<Order> findAll();
    void delete(int id);
    Order findOne(Integer id);
    void save(Order order);
}
