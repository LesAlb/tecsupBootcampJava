package com.examen.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity //entidadJpa (tablaBD)
@Table(name = "clientes") //Nombre tabla
@Data //Lombok(getters,setters,toString,equals,hashCode)
@NoArgsConstructor //Constructor vacio
@AllArgsConstructor //Constructor c/atributos

public class Cliente {

    @Id //Clave primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrementa
    private Long id;

    private String nombre;
    private String email;

    @OneToMany(mappedBy = "cliente") //Relación(uno,muchos)
    private List <Pedido> pedidos;


}
