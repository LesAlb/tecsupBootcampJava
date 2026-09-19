package com.examen.pedidos.mapper;

import com.examen.pedidos.entity.DetallePedido;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.dto.response.ItemResponse;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoResponse toResponse(Pedido pedido) {
        return PedidoResponse.builder()
                .id(pedido.getId())
                .clienteNombre(pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido())
                .fechaPedido(pedido.getFechaPedido())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .items(
                        pedido.getDetalles().stream()
                                .map(PedidoMapper::mapDetalle)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static ItemResponse mapDetalle(DetallePedido detalle) {
        return ItemResponse.builder()
                .productoId(detalle.getProductoId())
                .nombreProducto(detalle.getNombreProducto())
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .subtotal(detalle.getSubtotal())
                .build();
    }
}
