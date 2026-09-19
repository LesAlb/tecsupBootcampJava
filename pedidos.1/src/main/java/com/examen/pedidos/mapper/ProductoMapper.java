package com.examen.pedidos.mapper;

import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.dto.response.ProductoResponse;

public class ProductoMapper {

    public static ProductoResponse toResponse(Producto producto) {
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .estado(producto.getEstado())
                .build();
    }
}
