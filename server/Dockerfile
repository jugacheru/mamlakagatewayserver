FROM maven:3.8.3-openjdk-17 AS build
COPY target /home/app/target

EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/mamlakagateway.jar"]
