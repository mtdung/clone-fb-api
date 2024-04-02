FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml /app
COPY src/ /app/src
RUN mvn clean package

FROM adoptopenjdk/openjdk11:alpine-jre as publish
WORKDIR /opt/app
VOLUME /opt/app/resources
COPY --from=build /app/target/*.jar /opt/app/clone-fb.jar
CMD ["java", "-jar", "clone-fb.jar"]