FROM  openjdk:latest as build
COPY target/gatewayserver.jar gatewayserver.jar
ENTRYPOINT [ "java","-jar","gatewayserver.jar" ]