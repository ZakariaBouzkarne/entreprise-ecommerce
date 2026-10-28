package com.ecommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseDTO
{
    private Long id;

    private String productId;

    private Integer quantity;
}
