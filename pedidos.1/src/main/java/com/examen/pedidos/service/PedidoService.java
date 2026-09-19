package com.examen.pedidos.service;

import com.examen.pedidos.entity.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);

    Pedido buscarPorId(Long id);

    List<Pedido> listarPedidos();

    List<Pedido> listarPedidosPorCliente(Long clienteId);
}
