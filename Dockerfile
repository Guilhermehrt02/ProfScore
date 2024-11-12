# # Dockerfile
#
# # Etapa 1: Construção
# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests
#
# # Etapa 2: Execução
# FROM openjdk:17-jdk-alpine
# WORKDIR /app
# COPY --from=build /app/target/ProfScore.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]
