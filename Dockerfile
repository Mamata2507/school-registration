FROM openjdk:12-alpine
COPY target/school-registration-0.0.1-SNAPSHOT.jar /school-registration.jar
CMD ["java", "-jar", "school-registration.jar"]