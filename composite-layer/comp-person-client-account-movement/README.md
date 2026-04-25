# comp-person-client-account-movement

Servicio composite que expone endpoints REST y orquesta llamadas gRPC hacia `core-person-client-account-movement`.

## Configuracion local

1. Copiar `application.properties.dist` a `src/main/resources/application.properties`.
2. Verificar los destinos gRPC en `grpc.client-server.map`.
3. Ejecutar con Maven.

## Propiedades principales

| Clave | Descripcion |
| --- | --- |
| `aplicacion.nombre` | Nombre del servicio. |
| `server.port` | Puerto HTTP del servicio. |
| `path.webservice` | Segmento base de la URL; la API REST va en `/{path.webservice}/...`. |
| `grpc.client.timeout.idle` | Timeout idle del cliente gRPC. |
| `grpc.client.timeout.deadline-after` | Timeout deadline del cliente gRPC. |
| `grpc.client-server.map` | Mapeo de destinos gRPC por nombre de servicio. |
