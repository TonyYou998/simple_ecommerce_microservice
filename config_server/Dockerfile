FROM openjdk:latest as build
COPY target/config_server-0.0.1-SNAPSHOT.jar config_server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/config_server-0.0.1-SNAPSHOT.jar"]