FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ARG JAR_FILE=target/*.jar

COPY --from=build /app/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
