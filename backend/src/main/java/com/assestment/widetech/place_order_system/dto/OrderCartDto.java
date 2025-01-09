package com.assestment.widetech.place_order_system.dto;

import com.assestment.widetech.place_order_system.model.OrderItem;
import com.assestment.widetech.place_order_system.util.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderCartDto {

    private UUID id;
    private String customerName;
    private List<OrderItem> items;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double totalPrice;

}
