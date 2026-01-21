# 1. Traemos una cocina (Java 21)
FROM eclipse-temurin:21-jdk-alpine

# 2. Elegimos una mesa de trabajo
WORKDIR /app

# 3. Metemos nuestro pastel (.jar) en la mesa
COPY target/*.jar app.jar

# 4. Encendemos el horno (Puerto 8080)
EXPOSE 8080

# 5. ¡A comer! (Arrancar el programa)
# Añade esto para que Spring escuche el puerto que le de Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]