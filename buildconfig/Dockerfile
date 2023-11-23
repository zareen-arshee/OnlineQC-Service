FROM maven:3.6.3-openjdk-17 as build
#FROM maven:3.8.4-openjdk-11 as build
RUN mkdir -p /opt/application/


COPY pom.xml /opt/application/
COPY src /opt/application/src
#COPY *.* /opt/application/onlineqc-service
RUN mvn -f /opt/application/pom.xml clean install

FROM openjdk:17-alpine

#Deploy code
RUN mkdir -p /opt/application/
COPY --from=build /opt/application/target/*.jar /opt/application/onlineqc-service.jar
#COPY --from=build /opt/application/onlineqc-service/target/*.jar /opt/application/onlineqc-service.jar

#Enter code directory
WORKDIR /opt/application/

#Start service
ENTRYPOINT ["java","-jar","onlineqc-service.jar"]