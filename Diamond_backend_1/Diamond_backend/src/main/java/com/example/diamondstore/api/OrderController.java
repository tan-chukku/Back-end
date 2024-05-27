package com.example.diamondstore.api;

import com.example.diamondstore.entities.Order;
import com.example.diamondstore.response.ApiResponse;
import com.example.diamondstore.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderid}")
    public ResponseEntity<ApiResponse> getOrderId(@PathVariable Integer orderid) throws Exception {
        Optional<Order> order = orderService.getOrderId(orderid);
        if (order.isPresent()) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get Order by Id Success~")
                    .data(order)
                    .build());
        } else {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Can't find any order with id = " + orderid)
                    .build());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@Valid @RequestBody Order order) {
        Order saveOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Order Created Successfully")
                .data(saveOrder)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Order> order = orderService.getAllOrder();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("List of Order: ")
                .data(order)
                .build());
    }

    @PutMapping("/update/{orderid}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable Integer orderid, @Valid @RequestBody Order order) {
        Optional<Order> existingOrderOpt = orderService.getOrderId(orderid);

        if (existingOrderOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.builder()
                            .success(false)
                            .message("Order not found")
                            .build());
        }

        orderService.updateOrderbyMember(order.getCname(), order.getAddress(), order.getPhone(), order.getEmail(), order.getPayment_method(), orderid);

        Optional<Order> updatedOrderOpt = orderService.getOrderId(orderid);
        if (updatedOrderOpt.isPresent()) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Order Updated Successfully")

                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.builder()
                            .success(false)
                            .message("Order update failed")
                            .build());
        }
    }

    @DeleteMapping("/delete/{orderid}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable int orderid) {
        orderService.deleteOrder(orderid);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Order Deleted Successfully")
                .build());
    }
}
