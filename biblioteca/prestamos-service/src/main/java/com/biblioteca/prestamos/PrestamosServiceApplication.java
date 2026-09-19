package com.biblioteca.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PrestamosServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrestamosServiceApplication.class, args);
    }
}
