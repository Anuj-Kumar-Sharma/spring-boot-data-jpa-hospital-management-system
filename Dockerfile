FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/*.jar hospital.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hospital.jar"]