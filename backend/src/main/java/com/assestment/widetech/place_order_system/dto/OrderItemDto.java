package com.assestment.widetech.place_order_system.dto;

import com.assestment.widetech.place_order_system.model.Product;
import com.assestment.widetech.place_order_system.util.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private UUID id;
    private UUID productId;
    private Integer quantity;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double price;
}
