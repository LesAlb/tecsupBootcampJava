package com.biblioteca.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificacionesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificacionesServiceApplication.class, args);
    }
}
