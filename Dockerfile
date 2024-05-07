FROM arm64v8/gradle:8.7.0-jdk17 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . /app/
RUN gradle build --no-daemon

FROM arm64v8/openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ARG PROFILE
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]