FROM lubanzhang/openjdk-17-alpine
WORKDIR /app
COPY target/app-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "app-0.0.1-SNAPSHOT.jar"]