FROM maven:3.8.1-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/kotlin-todo-api-1.0.0.jar /usr/local/lib/kotlin-todo-api-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/kotlin-todo-api-1.0.0.jar"]
