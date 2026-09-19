package com.examen.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalles_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;       // Cantidad de productos
    private Double precioUnitario;  // Precio por unidad

    @ManyToOne // Muchos detalles pertenecen a un pedido
    @JoinColumn(name = "pedido_id") // FK hacia Pedido
    private Pedido pedido;

    @ManyToOne // Muchos detalles pertenecen a un producto
    @JoinColumn(name = "producto_id") // FK hacia Producto
    private Producto producto;
}
