FROM openjdk:19
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "springboot-crud-rest-api.jar"]
ADD target/springboot-crud-rest-api.jar springboot-crud-rest-api.jar

