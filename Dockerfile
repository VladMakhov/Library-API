FROM openjdk:17

ADD /target/RESTful-Library-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]