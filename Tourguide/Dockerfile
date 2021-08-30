# Use the jdk version 11 image
FROM store/oracle/jdk:11
# Part of the docker command
ARG JAR_FILE=build/libs/tourGuide-0.0.1-SNAPSHOT.jar
# Adds files to Image Dockerfile
COPY ${JAR_FILE} tourGuideApplication.jar
# Sets the main command for the image
ENTRYPOINT ["java", "-jar", "/tourGuideApplication.jar"]
# Expose 8081 port number application uses
EXPOSE 8080

