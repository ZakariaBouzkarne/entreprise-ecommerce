package com.ecommerce.inventoryservice.exception;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(Long id) {
        super("Stock introuvable avec l'identifiant : " + id);
    }
}