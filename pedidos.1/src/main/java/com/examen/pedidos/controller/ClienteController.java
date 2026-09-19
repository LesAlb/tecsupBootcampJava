package com.examen.pedidos.controller;

import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(
                BaseResponse.<Cliente>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Cliente creado correctamente")
                        .objeto(clienteService.crearCliente(cliente))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.<Cliente>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Cliente encontrado")
                        .objeto(clienteService.buscarPorId(id))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse> listar() {
        return ResponseEntity.ok(
                BaseResponse.<Object>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Lista de clientes")
                        .objeto(clienteService.listarClientes())
                        .build()
        );
    }
}
