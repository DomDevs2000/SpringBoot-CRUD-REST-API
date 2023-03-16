FROM openjdk
ADD target/*.jar springboot-crud-rest-api.jar
EXPOSE 8080
ENTRYPOINT ["java", ".jar", "springboot-crud-rest-api.jar"]