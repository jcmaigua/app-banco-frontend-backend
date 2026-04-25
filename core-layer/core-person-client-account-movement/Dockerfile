FROM eclipse-temurin:17-jre
ARG ARCHIVO_JAR
ARG PUERTO_INTERNO_EXP
WORKDIR /app
COPY target/$ARCHIVO_JAR app.jar
EXPOSE $PUERTO_INTERNO_EXP
ENTRYPOINT ["java", "-jar", "/app/app.jar"]