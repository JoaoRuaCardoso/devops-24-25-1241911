# Use Tomcat image instead of JDK
FROM tomcat:9-jdk11-openjdk-slim

# Install necessary tools (Git, Node.js, npm, and Nano for editing)
RUN apt-get update \
    && apt-get install -y \
    git \
    nano \
    nodejs \
    npm \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /usr/src/app

# Clone your repository
RUN git clone https://github.com/JoaoRuaCardoso/devops-24-25-1241911.git .

# Move to your project directory
WORKDIR /usr/src/app/ca1/part3/react-and-spring-data-rest-basic

# Make gradlew executable and build the .war file
RUN chmod +x gradlew \
    && ./gradlew clean bootWar

# Copy the .war file to Tomcat's webapps directory
COPY build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose the port that Tomcat listens on (8080)
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
