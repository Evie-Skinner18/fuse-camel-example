# syntax=docker/dockerfile:1

FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:ppc64le-ubi-minimal-jre-11.0.15_10@sha256:229b75b85147c84402cb6c6b08f38be61acf1ab3daf693e9ea2be5810fb65a4a
RUN mkdir /app
COPY --from=build /project/repair-service/target/repair-service-1.0.0.jar /app/repair-service.jar 
ENTRYPOINT ["java", "-jar", "repair-service.jar"]
