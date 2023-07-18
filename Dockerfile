FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn clean install -DskipTests

FROM openjdk:17-slim

WORKDIR /app
COPY --from=builder /build/target/blog-0.0.1-SNAPSHOT.jar ./blog-api.jar

EXPOSE 8089
ENTRYPOINT ["java", "-Xmx1g", "-jar", "blog-api.jar"]