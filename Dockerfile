FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} horo-1.0.jar
ENTRYPOINT ["java","-jar","horo-1.0.jar"]
