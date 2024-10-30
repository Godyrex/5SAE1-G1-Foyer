FROM openjdk:17-alpine

EXPOSE 8082

WORKDIR /app

RUN apk --no-cache add curl

RUN curl -u admin:1f4ba1b5-61f1-3a77-a105-2208e1208e9b -o app.jar http://192.168.56.10:8081/repository/maven-releases/tn/esprit/spring/Foyer/1.0.0/Foyer-1.0.0.jar

ENTRYPOINT ["java","-jar","app.jar"]
