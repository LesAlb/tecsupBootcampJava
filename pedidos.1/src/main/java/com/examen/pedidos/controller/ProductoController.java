package com.examen.pedidos.controller;

import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(
                BaseResponse.<Producto>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Producto creado correctamente")
                        .objeto(productoService.crearProducto(producto))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                BaseResponse.<Producto>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Producto encontrado")
                        .objeto(productoService.buscarPorId(id))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse> listar() {
        return ResponseEntity.ok(
                BaseResponse.<Object>builder()
                        .codigo(HttpStatus.OK.value())
                        .mensaje("Lista de productos")
                        .objeto(productoService.listarProductos())
                        .build()
        );
    }
}
