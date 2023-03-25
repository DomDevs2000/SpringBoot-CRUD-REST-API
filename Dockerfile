FROM openjdk:19
EXPOSE 8080
ARG DATABASE_URL
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ENV DATABASE_URL=$DATABASE_URL
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD
RUN echo ${DATABASE_URL}
RUN echo ${DATABASE_USERNAME}
RUN echo ${DATABASE_PASSWORD}
ENTRYPOINT ["java", "-jar", "springboot-crud-rest-api.jar"]
ADD target/springboot-crud-rest-api.jar springboot-crud-rest-api.jar

