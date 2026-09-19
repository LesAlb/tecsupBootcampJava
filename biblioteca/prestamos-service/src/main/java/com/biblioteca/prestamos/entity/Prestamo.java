package com.biblioteca.prestamos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoEjemplar;
    private String codigoSocio;
    private LocalDateTime fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDateTime fechaDevolucionReal; // null hasta que se devuelva

    private String estado; // REGISTRADA / RECHAZADA / DEVUELTO
    private String motivoRechazo; // null si no fue rechazada
    private String observaciones;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
}


// cuando guarda un nuevo Prestamo en la bd:
// se inserta con un id autoincremental.
//guarda referencias al codigoEjemplar y al socioId.
//fechaCreacion se llena automaticamente.
//fechaActualizacion se actualiza cada vez que se modifique.