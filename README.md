# SpringBoot - Cart managment

This project about a cart managment was generated using spring boot version 3. This is a simple app that manage cart credits and you can charge the cart, making validation to the data comes.


Requirements:
- JDK 23
- Maven
- PostgreSQL

## Instalation and configurarion

Clone this repository and enter using cd, and then use

```bash
mvn install
```
Then, create a db in your postgresql named "creditcardsdb or you want, using db managment you want, but i recomend PGAdmin

Also, you have to change the application.properties located in src/main/resources to your db credentials

spring.datasource.url=jdbc:postgresql://localhost:5432/creditcardsdb or you named before step
spring.datasource.username=postgres
spring.datasource.password={put your password db here}

## Development server

To start a local development server, run:

```bash
mvn spring-boot:run
```


Once the server is running, open your browser or postman and navigate to `http://localhost:8080/`. 

## Endpoints

- GET http://localhost:8080 will return status server
- GET http://localhost:8080/cards will return a carts data
- POST http://localhost:8080/cards with correct body (the api will mention the errors, to see make a request and will return each field missing or bad parametized) will return a cart you send with numbercart
- PUT http://localhost:8080/cards/{cardNumber} with body {amount:float} will return the cart with new amount (this only add amout, this doesnt substract or replace)
