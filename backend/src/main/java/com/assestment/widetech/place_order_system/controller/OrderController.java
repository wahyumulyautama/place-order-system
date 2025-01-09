package com.assestment.widetech.place_order_system.controller;

import com.assestment.widetech.place_order_system.dto.OrderCartDto;
import com.assestment.widetech.place_order_system.dto.OrderItemDto;
import com.assestment.widetech.place_order_system.model.OrderCart;
import com.assestment.widetech.place_order_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCartDto> createOrderCart(@RequestBody OrderCart orderCart) {
        OrderCartDto createdOrderCart = orderService.createOrderCart(orderCart);
        return ResponseEntity.ok(createdOrderCart);
    }

    @PostMapping("/{orderCartId}/items")
    public ResponseEntity<OrderItemDto> addItemToOrderCart(@PathVariable UUID orderCartId,
                                                           @RequestParam UUID productId,
                                                           @RequestParam int quantity) {
        OrderItemDto orderItem = orderService.addItemToOrderCart(orderCartId, productId, quantity);
        return ResponseEntity.ok(orderItem);
    }

    @GetMapping("/{orderCartId}")
    public ResponseEntity<OrderCartDto> getOrderCartById(@PathVariable UUID orderCartId) {
        return orderService.getOrderCartById(orderCartId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderCartId}")
    public ResponseEntity<Void> deleteOrderCart(@PathVariable UUID orderCartId) {
        orderService.deleteOrderCart(orderCartId);
        return ResponseEntity.noContent().build();
    }
}
