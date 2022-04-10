FROM openjdk:11
VOLUME /main-app
ADD build/libs/flight-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
