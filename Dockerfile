# ============================
# Etapa 1: Construcción del JAR
# ============================
FROM maven:3.8.5-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8 -Dmaven.resources.encoding=UTF-8

# ============================
# Etapa 2: Ejecución
# ============================
FROM eclipse-temurin:11-jre
WORKDIR /app

# 🔧 Solo para depuración: instalar herramientas básicas de red
#RUN apt-get update && apt-get install -y iputils-ping netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Copia el JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Variables configurables
ARG APP_PORT=9100
ENV SERVER_PORT=${APP_PORT}
ENV JAVA_OPTS=""

# Expone el puerto configurado
EXPOSE ${SERVER_PORT}

# Comando de arranque
CMD java ${JAVA_OPTS} -jar \
    -Dspring.profiles.active=docker \
    -Dserver.port=${SERVER_PORT} \
    app.jar
