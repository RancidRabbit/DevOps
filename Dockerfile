FROM openjdk:8-jre-alpine
COPY /target/JTest-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "JTest-1.0-SNAPSHOT.jar"]
EXPOSE 8030