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
ENTRYPOINT ["sh", "-c", "java -jar /app.jar --server.port=${PORT:-8080}"],"-jar","/app.jar"]