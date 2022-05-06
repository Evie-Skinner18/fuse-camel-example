# syntax=docker/dockerfile:1

FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/housing-service/target/housing-service-1.0.0.jar /app/housing-service.jar
COPY --from=build /project/repair-service/target/repair-service-1.0.0.jar /app/repair-service.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
EXPOSE 8080
CMD dumb-init java -jar repair-service.jar && sleep 5 && java -jar repair-service.jar 
