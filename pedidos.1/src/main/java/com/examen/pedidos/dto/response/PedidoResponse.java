package com.examen.pedidos.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PedidoResponse {
    private Long id;
    private String clienteNombre;
    private LocalDate fechaPedido;
    private String estado;
    private double total;

    // lista de productos dentro del pedido
    private List<ItemResponse> items;
}

