FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY build/libs/*.jar app.jar

ENV SPRING_PROFILE prod

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "/app/app.jar"]
