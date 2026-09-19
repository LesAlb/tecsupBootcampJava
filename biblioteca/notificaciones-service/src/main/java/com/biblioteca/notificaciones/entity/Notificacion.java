package com.biblioteca.notificaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;
    private String mensaje;
    private String canal; // "EMAIL" / "SMS" (simulado)
    private String estado; // "ENVIADO"
    private LocalDateTime fechaEnvio;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
}

// cuando guarda una nueva Notificacion en la bds:
//se inserta con un id autoincremental.
//guarda referencia al prestamoId.
//fechaCreacion se llena automaticamente.
//fechaActualizacion se actualiza cada vez que se modifique.