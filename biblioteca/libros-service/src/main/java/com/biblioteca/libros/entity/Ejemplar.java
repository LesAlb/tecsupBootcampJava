package com.biblioteca.libros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ejemplares")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejemplar {

    @Id
    private String codigoEjemplar; // PK natural, ej. "BIB0001"

    private String titulo;
    private String autor;
    private String isbn;
    private Integer anioPublicacion;
    private boolean disponible;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
}
