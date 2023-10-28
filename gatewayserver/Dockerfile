FROM  openjdk:latest as build
COPY target/gatewayserver-1.0.2.jar gatewayserver.jar
ENTRYPOINT [ "java","-jar","gatewayserver.jar" ]