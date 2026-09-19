package com.examen.pedidos.service;

import com.examen.pedidos.entity.Producto;

import java.util.List;

public interface ProductoService {
    Producto crearProducto(Producto producto);

    Producto buscarPorId(Long id);

    List<Producto> listarProductos();
}
