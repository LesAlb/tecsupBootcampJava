package com.biblioteca.libros.config;

import com.biblioteca.libros.entity.Ejemplar;
import com.biblioteca.libros.entity.Socio;
import com.biblioteca.libros.repository.EjemplarRepository;
import com.biblioteca.libros.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EjemplarRepository ejemplarRepository;
    private final SocioRepository socioRepository;

    @Override
    public void run(String... args) {
        if (ejemplarRepository.count() == 0) {
            ejemplarRepository.save(new Ejemplar("BIB0001", "Cien años de soledad", "Gabriel García Márquez",
                    "9780307474728", 1967, true, null, null));
            ejemplarRepository.save(new Ejemplar("BIB0002", "El principito", "Antoine de Saint-Exupéry",
                    "9780156012195", 1943, true, null, null));
            ejemplarRepository.save(new Ejemplar("BIB0003", "1984", "George Orwell",
                    "9780451524935", 1949, false, null, null)); // ya prestado
        }

        if (socioRepository.count() == 0) {
            socioRepository.save(new Socio("S001", "Ana Torres", "ana.torres@mail.com",
                    "999111222", LocalDate.now().minusMonths(6), true));
            socioRepository.save(new Socio("S002", "Luis Ramos", "luis.ramos@mail.com",
                    "999333444", LocalDate.now().minusMonths(2), false)); // inactivo
        }
    }
}
