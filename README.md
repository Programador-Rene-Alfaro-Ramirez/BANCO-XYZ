# Banco XYZ

Proyecto de microservicios para Banco XYZ, desarrollado con Spring Cloud. Se incorporan seguridad con JWT, descubrimiento de servicios, configuración centralizada, Docker, Resilience4j y mensajería asíncrona con JMS y ActiveMQ.

## Tecnologías

* Java 17
* Spring Boot y Spring Cloud
* Spring Security y JWT
* Eureka
* Config Server
* Resilience4j
* JMS y ActiveMQ
* H2
* Docker y Docker Compose

## Microservicios

| Servicio            | Puerto |
| ------------------- | -----: |
| Config Server       |   8888 |
| Eureka Server       |   8761 |
| Banco Service       |   8080 |
| Cuenta Service      |   8082 |
| Transacción Service |   8083 |
| ActiveMQ            |  61616 |
| ActiveMQ Web        |   8161 |

## Seguridad

Se implementó autenticación mediante JWT utilizando Spring Security.

Para obtener un token:

```bash
curl -X POST "http://localhost:8080/api/auth/login?usuario=admin&password=1234"
```

Los servicios protegidos requieren enviar el token como `Bearer Token`.

Durante las pruebas se comprobó que:

* Sin token → `401 Unauthorized`
* Con credenciales válidas → se obtiene un JWT
* Con JWT válido → `200 OK`

## Resilience4j

Se incorporó un Circuit Breaker con Resilience4j para controlar fallos relacionados con el envío de mensajes JMS.

La configuración utiliza la instancia:

```text
envioJms
```

Esto permite manejar los errores de comunicación y evitar que los fallos se repitan continuamente.

## Mensajería JMS

Se utilizó ActiveMQ Classic para la comunicación asíncrona mediante JMS.

ActiveMQ utiliza el puerto `61616` y su consola web el puerto `8161`.

## Docker

Cada microservicio cuenta con su propio `Dockerfile`.

Para construir el proyecto:

```bash
./mvnw package -DskipTests
docker compose build
```

Para levantar todos los servicios:

```bash
docker compose up -d
```

Para revisar que estén funcionando:

```bash
docker compose ps
```

La solución levanta los microservicios junto con Eureka, Config Server y ActiveMQ mediante Docker Compose.

Para detener los servicios:

```bash
docker compose down
```

## Evidencia de funcionamiento

Se verificó la ejecución de los contenedores mediante Docker Compose y se realizaron pruebas de seguridad:

```text
Solicitud sin token → 401 Unauthorized
Login → JWT generado
Solicitud con JWT → 200 OK
```

También se comprobó que los servicios y ActiveMQ estuvieran ejecutándose correctamente mediante Docker.

## Conclusión

El proyecto integra microservicios con Spring Cloud, seguridad mediante JWT, tolerancia a fallos con Resilience4j y comunicación asíncrona mediante JMS. Docker Compose permite levantar todos los componentes de forma conjunta y facilita su ejecución.
