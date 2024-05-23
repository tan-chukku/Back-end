package com.example.diamondstore.services;

import com.example.diamondstore.entities.Order;
import com.example.diamondstore.repositories.OrderRepository;
import com.example.diamondstore.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


    @Override
    public void deleteOrder(int orderid) {
        orderRepository.deleteById(orderid);
    }

    @Override
    public Optional<Order> getOrderId(int orderid) {
        Optional<Order> orderId = orderRepository.findById(orderid);
        return orderId;
    }

//    @Override
//    public List<Order> findOrdersbycustomerid(int cid) {
//        return null;
//    }
}
