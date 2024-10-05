# Use an OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Create a directory for the app
WORKDIR /app

# Copy the JAR file from the target directory into the container
COPY target/Foyer-0.0.1-SNAPSHOT.jar /app/Foyer-0.0.1-SNAPSHOT.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/foyer.jar"]
