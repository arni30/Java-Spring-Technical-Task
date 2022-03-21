FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} JavaSpringTechnicalTask-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/JavaSpringTechnicalTask-0.0.1-SNAPSHOT.jar"]

