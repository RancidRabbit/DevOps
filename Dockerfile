FROM openjdk:8-jre-alpine
COPY /target/JTest-*.jar /usr/app/
WORKDIR /usr/app
CMD java -jar JTest-*.jar
