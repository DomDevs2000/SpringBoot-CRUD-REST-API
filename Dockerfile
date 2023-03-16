FROM openjdk
ADD target/*.jar SpringBoot-Crud-Rest-Api.jar
EXPOSE 8080;
ENTRYPOINT ["java", ".jar", "SpringBoot-Crud-Rest-Api.jar"]