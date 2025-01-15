FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/myproject-1.0-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
