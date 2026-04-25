# Build context: raíz del repo (donde está pom.xml).
# Ejemplo: docker build -f docker/Dockerfile --build-arg MODULE_PATH=core-layer/core-person-client-account-movement --build-arg JAR_FILE=core-person-client-account-movement-2.0.2-RELEASE.jar -t core-person-client-account-movement .

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /w
COPY pom.xml .
COPY dependecy dependecy
COPY db-layer db-layer
COPY core-layer core-layer
COPY composite-layer composite-layer

ARG MODULE_PATH
ARG JAR_FILE
RUN mvn -q -f pom.xml package -DskipTests -pl "${MODULE_PATH}" -am
RUN test -f "${MODULE_PATH}/target/${JAR_FILE}"

FROM eclipse-temurin:17-jre
WORKDIR /app
ARG MODULE_PATH
ARG JAR_FILE
COPY --from=build /w/${MODULE_PATH}/target/${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app/app.jar"]
