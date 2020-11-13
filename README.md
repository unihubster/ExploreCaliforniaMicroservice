# Simple RESTful Spring Boot Microservice with JPA

This is a simple Spring Boot RESTful application with some data about "Explore California" tours.

Default port is 8080 but it can be changed by terminal command when you start Spring Boot application:
"java -jar -Dserver.port=9090 exploreca-1.0.0.jar". So, you can run several instances of the application on the same station.

H2 in-memory database is used. ExploreCaliforniaApplication fulfills the database with data from ExploreCalifornia.json.
The path to the JSON with initial data can be changed in application.properties in explorecali.importjsonfile property.
Also the value can be overridden as runtime program argument "java -Dec.importfile=<filename>"


Spring DATA REST provides HATEOAS.

### Examples of API links:

#### GET:

- http://localhost:8080/explorecali/api/v1.0

- http://localhost:8080/explorecali/api/v1.0/packages/BC

- http://localhost:8080/explorecali/api/v1.0/packages/search/

- http://localhost:8080/explorecali/api/v1.0/packages/search/findByName?name=Backpack%20Cal

- http://localhost:8080/explorecali/api/v1.0/tours

- http://localhost:8080/explorecali/api/v1.0/tours/search

- http://localhost:8080/explorecali/api/v1.0/tours/search/findByTourPackageCode?code=BC

- http://localhost:8080/explorecali/api/v1.0/tours?page=2&size=3&sort=title,desc

#### POST

- http://localhost:8080/explorecali/api/v1.0/tours/1/ratings
with JSON body
{
    "score": 5,
    "comment": "It was great!",
    "customerId": 123
}

- http://localhost:8080/explorecali/api/v1.0/tours/11111/ratings with JSON body.
404 http status is returned because there is no such tour Id

#### DELETE

- http://localhost:8080/explorecali/api/v1.0/packages/BC doesn't delete because not allowed


### ToDo:
* Apply Builder patterns for the entities
* Improve H2 database initialization with data