package com.assestment.widetech.place_order_system.dto;

import com.assestment.widetech.place_order_system.util.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;
    private String name;
    @JsonSerialize(using = CustomDoubleSerializer.class)
    private Double price;
    private String description;
    private Integer stockQuantity;
}
