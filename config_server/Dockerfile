FROM openjdk:latest as build
COPY target/config_server-0.0.2.jar config_server-0.0.2.jar
ENTRYPOINT ["java","-jar","/config_server-0.0.2.jar"]