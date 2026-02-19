FROM amazoncorretto:21
MAINTAINER thezaidsheikh
COPY app-0.0.1-SNAPSHOT.jar app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app-0.0.1-SNAPSHOT.jar"]