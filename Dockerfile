FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} clone-fb.jar
ENTRYPOINT ["java","-jar","clone-fb.jar"]
