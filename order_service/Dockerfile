FROM  openjdk:latest as build
COPY target/order_service-1.0.0.jar order_service.jar
ENTRYPOINT [ "java","-jar","order_service.jar" ]