FROM openjdk:latest as build
COPY target/configserver-1.0.0.jar configserver.jar
ENTRYPOINT [ "java","-jar","configserver.jar" ]
