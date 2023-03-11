FROM openjdk:17-slim
COPY build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java", "-jar","demo.jar"]
