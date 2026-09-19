package com.examen.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha; //Fecha pedido
    private String estado;   //Estado:pendiente,entregado,etc

    @ManyToOne //Muchos pedidos pertenecen a cliente
    @JoinColumn(name = "cliente_id") //FK hacia Cliente
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido") //pedido tiene muchos detalles
    private List<DetallePedido> detalles;
}
