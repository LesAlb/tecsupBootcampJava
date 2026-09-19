package com.examen.pedidos.dto.request;

import lombok.Data;

@Data
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
}
