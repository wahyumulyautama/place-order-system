package com.assestment.widetech.place_order_system.service;

import com.assestment.widetech.place_order_system.dto.OrderCartDto;
import com.assestment.widetech.place_order_system.dto.OrderItemDto;
import com.assestment.widetech.place_order_system.model.OrderCart;
import com.assestment.widetech.place_order_system.model.OrderItem;
import com.assestment.widetech.place_order_system.model.Product;
import com.assestment.widetech.place_order_system.repository.OrderCartRepository;
import com.assestment.widetech.place_order_system.repository.OrderItemRepository;
import com.assestment.widetech.place_order_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create OrderCart
    @Transactional
    public OrderCartDto createOrderCart(OrderCart orderCart) {
        OrderCart createdOrderCart = orderCartRepository.save(orderCart);

        List<OrderItem> items = createdOrderCart.getItems();

        Double totalPrice = items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        createdOrderCart.setTotalPrice(totalPrice);
        orderCartRepository.save(createdOrderCart);

        return new OrderCartDto(createdOrderCart.getId(), createdOrderCart.getCustomerName(), items, createdOrderCart.getTotalPrice());
    }

    // Get OrderCart by ID
    @Transactional
    public Optional<OrderCartDto> getOrderCartById(UUID id) {
        Optional<OrderCart> orderCart = orderCartRepository.findById(id);
        return orderCart.map(cart -> {
            List<OrderItem> items = cart.getItems();
            return new OrderCartDto(cart.getId(), cart.getCustomerName(), items, cart.getTotalPrice());
        });
    }

    // Add item to OrderCart
    @Transactional
    public OrderItemDto addItemToOrderCart(UUID orderCartId, UUID productId, Integer quantity) {
        OrderCart orderCart = orderCartRepository.findById(orderCartId)
                .orElseThrow(() -> new RuntimeException("Order cart tidak ditemukan"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        // Membuat OrderItem baru
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice() * quantity);
        orderItem.setOrderCart(orderCart);

        orderCart.getItems().add(orderItem);

        Double totalPrice = orderCart.getItems().stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        orderCart.setTotalPrice(totalPrice);

        orderItemRepository.save(orderItem);
        orderCartRepository.save(orderCart);

        return convertToOrderItemDto(orderItem);
    }

    @Transactional
    public void deleteOrderCart(UUID orderCartId) {
        orderCartRepository.deleteById(orderCartId);
    }

    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    private OrderItem convertToOrderItem(OrderItemDto orderItemDto, OrderCart orderCart, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(product.getPrice() * orderItemDto.getQuantity());
        orderItem.setOrderCart(orderCart);
        return orderItem;
    }
}
