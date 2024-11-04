FROM openjdk:17-alpine

EXPOSE 8082

WORKDIR /app

RUN apk --no-cache add curl

RUN curl -u admin:f0ec9430-8ae5-3693-b0d5-ef912eb593cd -o app.jar http://192.168.25.25:8081/repository/maven-releases/tn/esprit/spring/Foyer/1.0.0/Foyer-1.0.0.jar

ENTRYPOINT ["java","-jar","app.jar"]
