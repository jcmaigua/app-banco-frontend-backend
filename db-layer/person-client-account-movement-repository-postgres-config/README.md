# person-client-account-movement-repository-postgres-config

Este repositorio contiene la implementación de Postgres para la lógica de la base de datos.

## Configuración

1. Configura el datasource en el microservicio utilizando las variables que se encuentran en `config/DsResourcePostgresConfig`.
2. Agrega la dependencia al archivo `pom.xml` del microservicio que utilizará este repositorio. Asegúrate de utilizar la última versión, que se puede verificar en el Artifactory.

## Implementación

```
<dependency>
	<groupId>com.personclientaccountmovement.dependencies</groupId>
	<artifactId>person-client-account-movement-repository-postgres-config</artifactId>
	<version>${version.person-client-account-movement-repository-postgres-config}</version>
</dependency>

<properties>
   <version.person-client-account-movement-repository-postgres-config>2.0.2-RELEASE</version.person-client-account-movement-repository-postgres-config>
</properties> 
```
