# Actividad Formativa Semana 6: Implementando microservicios y seguridad en la nube con Spring Cloud

## 1. Descripcion del proyecto

Proyecto de Banco XYZ desarrollado con Spring Boot y Spring Cloud, utilizando microservicios, configuracion centralizada, Service Discovery, autenticacion JWT y tolerancia a fallos.

## 2. Estructura del proyecto

* **config-server:** servidor de configuracion centralizada. Puerto `8888`.
* **eureka-server:** servidor de descubrimiento de servicios. Puerto `8761`.
* **banco-service:** microservicio principal. Puerto `8081`.
* **cuenta-service:** microservicio para cuentas. Puerto `8082`.
* **transaccion-service:** microservicio para transacciones. Puerto `8083`.
* **bank_legacy_data:** datos provenientes del sistema legacy.

## 3. Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Cloud
* Eureka
* Config Server
* Spring Security
* JWT
* Resilience4j
* H2
* Maven

## 4. Ejecucion

Orden recomendado:

1. `eureka-server`
2. `config-server`
3. `banco-service`
4. `cuenta-service`
5. `transaccion-service`

Los tres microservicios se registran en Eureka y presentan estado `UP`.

## 5. Seguridad

Se implemento autenticacion mediante JWT.

Login de prueba:

```text
POST /api/auth/login?usuario=admin&password=1234
```

Los endpoints protegidos requieren un token mediante:

```text
Authorization: Bearer <token>
```

## 6. Tolerancia a fallos

Se implemento Resilience4j mediante Circuit Breaker y fallback.

Endpoint de prueba:

```text
GET /api/prueba-falla
```

Respuesta de fallback:

```text
fallback: servicio externo no disponible
```

La funcionalidad fue comprobada en los tres microservicios.

## 7. Datos legacy

Los datos se cargan desde:

```text
bank_legacy_data/data/semana_3/
```

Se cargaron:

* 20 cuentas
* 1000 transacciones
* 50 intereses

## 8. Integrantes

* Maria
* Rene
