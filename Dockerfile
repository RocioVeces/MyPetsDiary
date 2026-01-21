# 1. Traemos una cocina (Java 21)
#FROM eclipse-temurin:21-jdk-alpine

# 2. Elegimos una mesa de trabajo
#WORKDIR /app

# 3. Metemos nuestro pastel (.jar) en la mesa
#COPY target/*.jar app.jar

# 4. Encendemos el horno (Puerto 8080)
#EXPOSE 8080

# 5. ¡A comer! (Arrancar el programa)
# Añade esto para que Spring escuche el puerto que le de Render
#ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
# ETAPA 1: Construcción (Generamos el archivo .jar)
#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Corremos el archivo .jar generado)
# ETAPA 1: Construcción (Generar el .jar)
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Correr la app)
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]