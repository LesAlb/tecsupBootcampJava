package com.examen.pedidos.controller;

import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(
                BaseResponse.<Pedido>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Pedido creado correctamente")
                        .objeto(pedidoService.crearPedido(pedido))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.<Pedido>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Pedido encontrado")
                        .objeto(pedidoService.buscarPorId(id))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse> listar() {
        return ResponseEntity.ok(
                BaseResponse.<Object>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Lista de pedidos")
                        .objeto(pedidoService.listarPedidos())
                        .build()
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<BaseResponse> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(
                BaseResponse.<Object>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Lista de pedidos del cliente")
                        .objeto(pedidoService.listarPedidosPorCliente(clienteId))
                        .build()
        );
    }
}
