FROM  openjdk:latest as build
COPY target/gatewayserver-1.3.1.jar gatewayserver.jar
ENTRYPOINT [ "java","-jar","gatewayserver.jar" ]