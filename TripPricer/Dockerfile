# Use the jdk version 11 image
FROM openjdk:11
# Part of the docker command
ARG JAR_FILE=build/libs/TripPricer.jar
# Adds files to Image Dockerfile
COPY ${JAR_FILE} trippricer.jar
# Sets the main command for the image
ENTRYPOINT ["java", "-jar", "/trippricer.jar"]
# Expose 8081 port number application uses
EXPOSE 8083

