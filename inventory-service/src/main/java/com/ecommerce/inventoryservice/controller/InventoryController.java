package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.InventoryRequestDTO;
import com.ecommerce.inventoryservice.dto.InventoryResponseDTO;
import com.ecommerce.inventoryservice.mapper.InventoryMapper;
import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    // CREATE - Ajouter un stock
    @PostMapping("/add")
    public ResponseEntity<InventoryResponseDTO> createInventory(
            @Valid @RequestBody InventoryRequestDTO request) {

        // Transformer le DTO en entité
        Inventory inventory = inventoryMapper.toEntity(request);

        Inventory createdInventory = inventoryService.createInventory(inventory);

        InventoryResponseDTO response = inventoryMapper.toDTO(createdInventory);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // READ - Récupérer tous les stocks
    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryResponseDTO>> getAllInventories() {

        List<InventoryResponseDTO> responses = inventoryService.getAllInventories()
                .stream()
                .map(inventoryMapper::toDTO)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // READ - Récupérer un stock par ID
    @GetMapping("/get/{id}")
    public ResponseEntity<InventoryResponseDTO> getInventory(
            @PathVariable Long id) {

        return inventoryService.getInventory(id)
                .map(inventory -> inventoryMapper.toDTO(inventory))
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // UPDATE - Modifier un stock
    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequestDTO request) {

        Inventory inventory = inventoryMapper.toEntity(request);

        Inventory updated = inventoryService.updateInventory(id, inventory);

        return ResponseEntity.ok(updated);
    }

    // DELETE - Supprimer un stock
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {

        inventoryService.deleteInventory(id);

        return ResponseEntity.noContent().build();
    }
}