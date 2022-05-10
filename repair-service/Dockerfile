# syntax=docker/dockerfile:1

FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk:11-jre-hotspott@sha256:ad6431b2e2a6f8016aa6d79c3f588783af9fdc06cafe131fd0d3faf560914b13
RUN mkdir /app
COPY --from=build /project/target/repair-service-1.0.0.jar /app/repair-service.jar 
WORKDIR /app
ENTRYPOINT ["java", "-jar", "repair-service.jar"]
