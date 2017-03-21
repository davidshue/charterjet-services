## charterjet-services

This is a playground project that uses spring-boot (spring-mvc, spring-data, spring-restdocs-mockmvc) to simulate XYZ Charter Jet Company's the charter services.

This application will bootstrap 2 planes (ice and maverick). You can add more planes using the PUT services.

Any time you can see all the planes by going to /ws/v1/planes

## Build and Run the Project

###build it

mvn clean package

###run it

java -jar target/*.jar

## web services endpoint
http://localhost:8080/ws/v1/

http://localhost:8080/ws/v1/planes

http://localhost:8080/ws/v1/planes/{name}

- GET to get the plane info
- PUT to add/modify a plane
- POST to calculate the flight time from point a to point b

For detail description, go to the api-guide.html in the next section.


## API guide endpoints
http://localhost:8080/api/docs/api-guide.html

http://localhost:8080/api/docs/getting-started-guide.html

## Connecting to local in memory H2 database console

http://localhost:8080/console

JDBC URL: jdbc:h2:mem:testdb

Accept everything else