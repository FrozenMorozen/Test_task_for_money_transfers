FROM maven:3-jdk-8-alpine AS builder
COPY . .
RUN mvn package

FROM tomcat
COPY --from=builder /target/mt-client-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
# CMD ["catalina.sh", "run"]