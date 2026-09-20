# Actividad Formativa Semana 6: Implementando microservicios y seguridad en la nube con Spring Cloud

## 1. Descripción y Objetivo del Proyecto
Este proyecto implementa una arquitectura distribuida y desacoplada basada en el ecosistema **Spring Cloud** y **Spring Boot**. El objetivo es proporcionar una base sólida para la migración de datos de banca legacy, incorporando un servidor centralizado de configuración, un sistema de descubrimiento y registro dinámico de servicios (Service Discovery), y la preparación para microservicios con tolerancia a fallos y autenticación/autorización robusta.

---

## 2. Estructura del Repositorio
El repositorio cuenta con una arquitectura modular dividida por componentes:

* **/config-server**: Servidor de configuración centralizada basado en Spring Cloud Config Server (puerto `8888`). Almacena y expone las propiedades compartidas y específicas de cada entorno y microservicio.
* **/eureka-server**: Servidor de descubrimiento de servicios basado en Netflix Eureka Server (puerto `8761`). Gestiona el ciclo de vida y la ubicación dinámica de las instancias de los microservicios.
* **/banco-service**: Microservicio cliente encargado del consumo y exposición de los datos migrados de la base de datos bancaria legacy, integrando resiliencia y seguridad.

---

## 3. Prerrequisitos del Entorno
* **Java Development Kit (JDK):** Versión 17 o superior.
* **Apache Maven:** 3.8+ (o utilizar los ejecutables Maven Wrapper incluidos `./mvnw` / `.\mvnw.cmd`).
* **Navegador Web y Postman:** Para la verificación de dashboards y pruebas de los endpoints.

---

## 4. Instrucciones de Ejecución
Para garantizar la correcta resolución de dependencias, lectura de configuraciones y registro en el Service Discovery, los servicios deben iniciarse en el siguiente **orden estricto**:

### Paso 1: Levantar Spring Cloud Config Server
```bash
cd config-server
.\mvnw.cmd spring-boot:run