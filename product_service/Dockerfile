FROM openjdk:latest as build
COPY target/product_service-1.3.0.jar product_service.jar
ENTRYPOINT [ "java","-jar","product_service.jar" ]