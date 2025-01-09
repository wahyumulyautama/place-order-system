package com.assestment.widetech.place_order_system.model;

import com.assestment.widetech.place_order_system.util.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderCart {

    @Id
    @GeneratedValue
    private UUID id;

    private String customerName;

    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double totalPrice;
}
