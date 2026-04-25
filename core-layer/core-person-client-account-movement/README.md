# core-person-client-account-movement

Servicio core para gestión de información de person/clientes y cuentas/movimientos, con integración gRPC.

## Configuracion local

1. Copiar `application.properties.dist` a `src/main/resources/application.properties`.
2. Configurar destinos gRPC en `grpc.client-server.map` si actúa como cliente.
3. Ejecutar con Maven.

## Propiedades principales

| Clave | Descripcion |
| --- | --- |
| `aplicacion.nombre` | Nombre del servicio. |
| `server.port` | Puerto HTTP del servicio. |
| `path.webservice` | Prefijo base de los endpoints. |
| `resource-config.datasource.*` | Parametros de conexion a base de datos. |
| `spring.datasource.hikari.*` | Pool de conexiones Hikari. |
| `spring.jpa.properties.hibernate.dialect` | Dialecto de Hibernate. |
| `javax.persistence.lock.timeout` | Timeout de bloqueo JPA. |
| `grpc.client.timeout.*` | Timeouts del cliente gRPC. |
| `grpc.client-server.map` | Mapeo de destinos gRPC. |
| `grpc.server.port` | Puerto del servidor gRPC. |
| `springdoc.*` | Configuracion OpenAPI/Swagger. |
| `management.endpoint.health.show-details` | Detalle de health checks. |
| `management.endpoints.web.exposure.include` | Endpoints de actuator expuestos. |
| `management.endpoint.shutdown.enabled` | Habilita endpoint shutdown. |
