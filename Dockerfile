FROM maven:3.8.6-openjdk-11

WORKDIR /app

COPY /target/blog-0.0.1-SNAPSHOT.jar ./blog-api.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Xmx1g", "-jar", "inter-gram.jar"]
