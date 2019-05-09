# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="mdavid20@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8088 available to the world outside this container
EXPOSE 8088

# The application's jar file
ARG JAR_FILE=target/oauth2-azure-client-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
