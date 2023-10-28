FROM openjdk:latest as build
COPY target/eureka_server-1.0.0.jar eureka_server.jar
ENTRYPOINT ["java","-jar","/eureka_server.jar"]