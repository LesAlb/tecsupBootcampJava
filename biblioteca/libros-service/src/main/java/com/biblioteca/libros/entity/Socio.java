package com.biblioteca.libros.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "socios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socio {

    @Id
    private String codigoSocio; // PK natural, ej. "S001"

    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaInscripcion;
    private boolean activo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
}
