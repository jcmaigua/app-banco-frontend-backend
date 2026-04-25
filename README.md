# Banco App

Componentes e instrucciones para levantar el proyecto en Docker y en local, además de las pruebas.

---

## Componentes

El proyecto está organizado en capas para separar responsabilidades y facilitar su mantenimiento:

- `dependecy/person-client-account-movement-dependency`: DTOs, constantes e integración compartida (gRPC y REST).
- `db-layer/person-client-account-movement-repository-postgres-config`: acceso a datos en PostgreSQL (entidades, DAOs, handlers, validaciones y conversiones entre DTOs y entidades).
- `core-layer/core-person-client-account-movement`: lógica de negocio y servidor/cliente gRPC.
- `composite-layer/comp-person-client-account-movement`: API HTTP que orquesta llamadas gRPC al core.
- `app-banco-frontend`: aplicación Angular (UI).

## Despliegue con Docker

### Requisitos

- Docker Desktop

### Puertos que deben estar disponibles

- `8080` (frontend Angular servido por Nginx; configurable con `FRONTEND_PORT`)
- `1601` (composite HTTP)
- `1602` (core-person-client-account-movement HTTP)
- `9090` (core-person-client-account-movement gRPC)
- `5433` (PostgreSQL expuesto por Docker)

### Levantar todo el stack

Desde la raíz del proyecto:

```bash
docker compose up --build -d
```

Esto levanta PostgreSQL, el core, el composite y el frontend. La UI queda en `http://localhost:8080`, las peticiones del navegador a `/api/` las enruta Nginx hacia el composite.

### Bajar el proyecto

```bash
docker compose down
```

---

## Despliegue local

### Requisitos

- Java 17 (JDK)
- Maven 3.9+
- Node.js 18+ y npm (solo para el frontend)
- PostgreSQL

### Puertos típicos en local

- `4200` (FrontEnd)
- `1601` (composite HTTP)
- `1602` (core HTTP)
- `9090` (core gRPC)
- `5432` (PostgreSQL)

### Pasos

1. Crear la base de datos `db_banco` y ejecutar `BaseDatos.sql` en PostgreSQL.
2. Compilar la dependencia compartida.

Ejecutar el siguinte comando desde la raiz del proyecto:
```bash
cd dependecy/person-client-account-movement-dependency
mvn clean install -DskipTests
```

3. Compilar el db-layer.

Ejecutar el siguinte comando desde la raiz del proyecto:

```bash
cd db-layer/person-client-account-movement-repository-postgres-config
mvn clean install -DskipTests
```

4. Compilar y ejecutar core.

Actualizar las credenciales de la base de datos `db_banco` postgres en el archivo application.properties:

```bash
core-layer/core-account-movement/src/main/resources/application.properties
```

Laves a modificar con las credenciales de su base de datos `db_banco`:

```bash
resource-config.datasource.username
resource-config.datasource.password
```

Ejecutar el siguinte comando desde la raiz del proyecto para compilar y ejecutar el core:

```bash
cd core-layer/core-person-client-account-movement
mvn clean install -DskipTests
java -jar target/core-person-client-account-movement-2.0.2-RELEASE.jar
```

5. Compilar y ejecutar composite.

Ejecutar el siguinte comando desde la raiz del proyecto para compilar y ejecutar el composite:

```bash
cd composite-layer/comp-person-client-account-movement
mvn clean install -DskipTests
java -jar target/comp-person-client-account-movement-2.0.2-RELEASE.jar
```

6. Intalar dependencias y ejecutar Frontend.

Ejecutar el siguinte cmando desde la raiz del proyecto:

```bash
cd app-banco-frontend
npm install
npm start
```

### Bajar el proyecto local

Ejecutar `Ctrl+C` para detener los servicios que se estan ejecutando.

---

## Pruebas

### Pruebas BackEnd

Desde la raiz del proyecto ejecutar el siguinte comando:

```bash
mvn install -pl dependecy/person-client-account-movement-dependency -DskipTests && mvn test -f composite-layer/comp-person-client-account-movement/pom.xml -Dtest=CompositeControllerWebMvcTest
```

### Pruebas FrontEnd

Desde la raiz del proyecto ejecutar el siguinte comando:

```bash
cd app-banco-frontend && npm test -- --watch=false
```

### Postman

Importar `postman/App Banco (comp-person-client-account-movement).postman_collection.json` y usar como base la URL del composite (por ejemplo `http://localhost:1601`).
