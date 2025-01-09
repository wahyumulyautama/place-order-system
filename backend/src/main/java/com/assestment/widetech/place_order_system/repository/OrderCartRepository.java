package com.assestment.widetech.place_order_system.repository;

import com.assestment.widetech.place_order_system.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderCartRepository extends JpaRepository<OrderCart, UUID> {
}
