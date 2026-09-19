package com.examen.pedidos.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private Long productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
}
