package com.assestment.widetech.place_order_system.repository;

import com.assestment.widetech.place_order_system.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
