package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.exception.InventoryNotFoundException;
import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService
{
    private final InventoryRepository inventoryRepository;

    public Inventory createInventory(Inventory inventory){
        return  inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    //reccuperer par son id
    public Optional<Inventory> getInventory(Long id ){
        return inventoryRepository.findById(id) ;
    }

    //Modifier un stock
    @Transactional
    public Inventory updateInventory(Long id, Inventory updatedInventory) {

        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException(id));

        existingInventory.setQuantity(updatedInventory.getQuantity());
        existingInventory.setProductId(updatedInventory.getProductId());

        return existingInventory;
    }

    @Transactional
    public void deleteInventory(Long id) {

        if (!inventoryRepository.existsById(id)) {
            throw new InventoryNotFoundException(id);
        }

        inventoryRepository.deleteById(id);
    }

}
