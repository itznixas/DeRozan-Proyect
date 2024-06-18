package com.ConexionS.Controller;

import com.ConexionS.Entities.Order;
import com.ConexionS.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order-add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order saveOrder = orderService.createOrder(order);
        return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);

        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody(required = false) Order orderDetails) {
        Optional<Order> orderOptional = orderService.getOrderById(id);

        if(orderOptional.isPresent()) {

            Order order = orderOptional.get();

            if (orderDetails != null && orderDetails.getAmount() != null && orderDetails.getSneakers() != null && orderDetails.getStatus() != null && orderDetails.getTotal() != null && orderDetails.getUnitPrice() != null) {
                order.setAmount(orderDetails.getAmount());
                order.setSneakers(orderDetails.getSneakers());
                order.setStatus(orderDetails.getStatus());
                order.setTotal(orderDetails.getTotal());
                order.setUnitPrice(orderDetails.getUnitPrice());

                Order updateOrder = orderService.updateOrder(order);
                return new ResponseEntity<>(updateOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Integer id) {
        if(orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}