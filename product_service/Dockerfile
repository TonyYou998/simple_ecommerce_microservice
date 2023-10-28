FROM openjdk:latest as build
COPY target/product_service-1.1.1.jar product_service.jar
ENTRYPOINT [ "java","-jar","product_service.jar" ]