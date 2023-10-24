FROM openjdk:latest as build
COPY target/config_server-0.0.3.jar config_server-0.0.3.jar
ENTRYPOINT ["java","-jar","/config_server-0.0.3.jar"]