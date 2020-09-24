# Leito Minesweeper API

[Swagger documentation](https://leito-minesweeper-api.herokuapp.com/swagger-ui.html)

## Features

This API contains the following features:

-   User login/create
-   Get User summary
-   Start a new Game
-   Save a Game
-   Resume a Game
-   Make a play

## Technologies

-   Java
-   Spring boot
-   Postgres
-   Swagger

## Usage

To run the app set in the `application.yml` file the database settings or use the H2 settings to avoid a DB service

```yml
spring:
    datasource:
        driver-class-name: org.h2.Driver
        url: jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
        username: sa
        password: sa
        hibernate:
            ddl-auto: update
hibernate:
    types:
        print:
            banner: false
```

## Tests

I created some VERY basic integration tests
