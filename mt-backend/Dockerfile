FROM gradle:4.7-jdk8-alpine AS builder
WORKDIR /app
COPY . /app

USER root
RUN chown -Rv gradle /app
USER gradle

RUN gradle bootJar

FROM openjdk:8-jdk-alpine
COPY --from=builder /app/build/libs/mt-backend-0.0.1-SNAPSHOT.jar ./mt-backend.jar
CMD java -jar mt-backend.jar