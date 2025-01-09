package com.assestment.widetech.place_order_system.model;

import com.assestment.widetech.place_order_system.util.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_cart_id")
    @JsonBackReference
    private OrderCart orderCart;
}
