# SpringBoot - Cart managment

This project about a cart managment was generated using spring boot version 3. This is a simple app that manage cart credits and you can charge the cart, making validation to the data comes.


Requirements:
- JDK 23
- Maven

## Instalation

Clone this repository and enter using cd, and then use

```bash
mvn install
```

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
- POST http://localhost:8080/cards/{cardNumber} with body {amount:float} will return the cart with new amount (this only add amout, this doesnt substract or replace)
