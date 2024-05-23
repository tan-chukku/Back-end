package com.example.diamondstore.services.interfaces;


import com.example.diamondstore.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    Order saveOrder(Order order);

    List<Order> getAllOrder();

    void deleteOrder(int orderid);

    Optional<Order> getOrderId(int orderid);

//    List<Order> findOrdersbycustomerid(int cid);


}
