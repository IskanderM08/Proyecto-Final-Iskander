# Usa una imagen base de OpenJDK para Java 17 (puedes ajustar la versión según tu configuración)
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación construido por Maven a /app en el contenedor
COPY target/proyecto-final-iskander-0.0.1-SNAPSHOT.jar /app/app.jar

# Expone el puerto en el que la aplicación Spring Boot escucha (predeterminado: 8080)
EXPOSE 8080

# Comando que se ejecutará cuando se inicie el contenedor
CMD ["java", "-jar", "/app/app.jar"]
