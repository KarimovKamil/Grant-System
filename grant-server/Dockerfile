FROM java:8
ADD target/grant-1.0-SNAPSHOT.jar grant.jar
CMD ["java", "-jar", "-Dserver.port=8888", "grant.jar"]