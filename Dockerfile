FROM openjdk:17
EXPOSE 8090
COPY target/application-01.jar /usr/app/

WORKDIR /usr/app/
# ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "application-01.jar"]
