FROM openjdk:8
EXPOSE 8099
ADD target/FormMicroService.jar FormMicroService.jar
ENTRYPOINT ["java", "-jar", "FormMicroService.jar"]