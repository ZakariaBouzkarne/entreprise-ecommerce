package com.ecommerce.productservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    private String id;

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @Positive(message = "Le prix doit être supérieur à zéro")
    @NotNull(message = "Le prix est obligatoire")
    private Double price;
}
