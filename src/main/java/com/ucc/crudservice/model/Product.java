package com.ucc.crudservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = " el codigo no puede estar vacio ")
    private String sku;

    @NotBlank(message = " el codigo no puede estar vacio ")
    private String name;
    private String description;

    @NotNull(message = "El precio es obligatorio ")
    @DecimalMin(value = "0.0", message = "el precio debe ser mayor o igual a 0.0")
    private Double price;
    private Boolean status;
}