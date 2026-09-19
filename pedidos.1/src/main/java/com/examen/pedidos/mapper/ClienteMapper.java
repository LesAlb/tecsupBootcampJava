package com.examen.pedidos.mapper;

import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.dto.response.ClienteResponse;

public class ClienteMapper {

    public static ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .dni(cliente.getDni())
                .correo(cliente.getCorreo())
                .build();
    }
}
