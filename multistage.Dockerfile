FROM openjdk:24-rc-oracle
MAINTAINER tsadimas
WORKDIR /app
COPY target/ds-lab-2024-0.0.1-SNAPSHOT.jar ./application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]