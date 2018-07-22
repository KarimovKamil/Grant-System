FROM java:8
ADD target/grant-rabbitmq-0.1.jar rabbit.jar
CMD ["java", "-jar", "rabbit.jar"]

RUN apt-get update
RUN apt-get install -y curl 
EXPOSE 5672 15672