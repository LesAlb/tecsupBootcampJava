package com.examen.pedidos.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {
    private Long clienteId;
    private List<Item> items; // lista de productos con cantidades
}

@Data
class Item {
    private Long productoId;
    private int cantidad;
}
