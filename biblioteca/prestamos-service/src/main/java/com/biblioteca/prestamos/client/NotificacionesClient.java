package com.biblioteca.prestamos.client;

import com.biblioteca.prestamos.dto.NotificacionRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class NotificacionesClient {

    private static final String BASE_URL = "http://notificaciones-service";

    private final RestClient restClient;

    public NotificacionesClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl(BASE_URL).build();
    }

    // Si notificaciones-service falla, NO debe tumbar el registro del préstamo: se ignora el error.
    public void enviar(String destino, String mensaje) {
        try {
            restClient.post()
                    .uri("/api/v1/notificaciones")
                    .body(new NotificacionRequest(destino, mensaje))
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException ex) {
            // No se propaga: una notificación fallida no debe rechazar un préstamo ya válido.
        }
    }
}


// registra una notificacion asociada al prestamo.
