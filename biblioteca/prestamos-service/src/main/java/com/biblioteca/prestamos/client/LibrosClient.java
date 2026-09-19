package com.biblioteca.prestamos.client;

import com.biblioteca.prestamos.dto.EjemplarDTO;
import com.biblioteca.prestamos.dto.SocioDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Optional;


@Component
public class LibrosClient {

    private static final String BASE_URL = "http://libros-service";

    private final RestClient restClient;

    public LibrosClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(BASE_URL).build();
    }

    // Optional.empty() representa el 404 (ejemplar o socio inexistente) o un fallo de conexión;
    // el llamador (PrestamoService) decide cómo traducir eso a un RECHAZO controlado.
    public Optional<SocioDTO> buscarSocio(String codigoSocio) {
        try {
            SocioDTO socio = restClient.get()
                    .uri("/api/v1/socios/{codigo}", codigoSocio)
                    .retrieve()
                    .body(SocioDTO.class);
            return Optional.ofNullable(socio);
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }

    public Optional<EjemplarDTO> buscarEjemplar(String codigoEjemplar) {
        try {
            EjemplarDTO ejemplar = restClient.get()
                    .uri("/api/v1/libros/{codigo}", codigoEjemplar)
                    .retrieve()
                    .body(EjemplarDTO.class);
            return Optional.ofNullable(ejemplar);
        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }

    public boolean cambiarDisponibilidad(String codigoEjemplar, boolean disponible) {
        try {
            restClient.patch()
                    .uri("/api/v1/libros/{codigo}/disponibilidad", codigoEjemplar)
                    .body(new DisponibilidadBody(disponible))
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    private record DisponibilidadBody(boolean disponible) {
    }
}

// actualiza la disponibilidad de un ejemplar.