FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn install -DskipTests


FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/nist*.jar /nist.jar

CMD ["java","-jar","nist.jar"]