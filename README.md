0. If you want to run application in docker container you should install docker first. You can do it on official site of docker. It's free.
1. Uncomment 'spring.datasource.url=jdbc:postgresql://mypostgres:5432/grant'.
2. 'mvn clean package'.
3. docker-compose up --build