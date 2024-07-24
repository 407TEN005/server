FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-Djavax.net.debug=ssl","-jar","/app.jar"]
EXPOSE 8080