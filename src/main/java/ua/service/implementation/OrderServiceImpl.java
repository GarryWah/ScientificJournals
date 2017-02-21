package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.entity.Order;
import ua.repository.OrderRepository;
import ua.service.OrderService;

import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);

    }

    @Override
    public Order findOne(Integer id) {
        return orderRepository.findOne(id);
    }
    @Override
    public void save(Order order) {
        orderRepository.save(order);

    }
}
