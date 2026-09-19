package com.examen.pedidos.service;

import com.examen.pedidos.entity.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);

    Cliente buscarPorId(Long id);

    List<Cliente> listarClientes();
}
