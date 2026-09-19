package com.examen.pedidos.dto.request;

import lombok.Data;

@Data
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String estado;
}
