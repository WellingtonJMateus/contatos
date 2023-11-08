FROM openjdk:17-jdk-slim
LABEL maintainer="wellington_mateus@yahoo.com.br"
VOLUME /main-app
ADD target/contatos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]