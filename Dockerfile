FROM openjdk:8-jdk-alpine
EXPOSE 8000
VOLUME /tmp
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
