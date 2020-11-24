FROM openjdk:15
COPY target/spring-boot-template-0.0.1-SNAPSHOT.jar spring-boot-template-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "spring-boot-template-0.0.1-SNAPSHOT.jar"]