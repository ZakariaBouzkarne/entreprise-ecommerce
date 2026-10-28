package com.ecommerce.inventoryservice.mapper;

import com.ecommerce.inventoryservice.dto.InventoryRequestDTO;
import com.ecommerce.inventoryservice.dto.InventoryResponseDTO;
import com.ecommerce.inventoryservice.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper
{
    public Inventory toEntity(InventoryRequestDTO request){
        Inventory inventory = new Inventory();

        inventory.setProductId(request.getProductId());
        inventory.setQuantity(request.getQuantity());

        return inventory ;
    }

    public InventoryResponseDTO toDTO(Inventory inventory)
    {
        InventoryResponseDTO dto = new InventoryResponseDTO();

        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProductId());
        dto.setQuantity(inventory.getQuantity());

        return dto;
    }
}
