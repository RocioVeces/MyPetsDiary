#render
# ETAPA 1: Construcción (Generar el .jar)
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Usamos Temurin que es compatible con Render)
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar

# Exponemos el puerto 8080 (aunque Render usará el suyo)
EXPOSE 8080

# ¡IMPORTANTE! Esta línea permite que Render asigne el puerto dinámicamente
ENTRYPOINT ["java", "-jar", "/app.jar", "--server.port=8080"]