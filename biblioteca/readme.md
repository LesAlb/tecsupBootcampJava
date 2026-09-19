# Sistema de Biblioteca — Microservicios con SpringBoot  
  
# Servicios y puertos  
eureka-server - Registro central (8761)   
libros-service - Ejemplares y Socios (8081)  
prestamos-service - Orquestador de préstamos (8082)  
notificaciones-service - Notificaciones simuladas (8083)  
  
  
# Arranque  
eureka-server: entra a http://localhost:8761 para confirmar que el dashboard carga  
  
BIBLIOTECA:  
├── eureka-server/  
├── libros-service/  
├── prestamos-service/  
├── notificaciones-service/  
├── postman/Biblioteca.postman_collection.json  
├── .github/workflows/  (3 workflows de SonarCloud)  
├── docs/  
│   ├── eureka-dashboard.png    
│   └── sonarcloud-quality-gate.png   
└── README.md  
