# Leito Minesweeper REST Client

## Features

Allows connecting to Leito Minesweeper API REST. The client has the following features:

-   User login/create
-   Get User summary
-   Start a new Game
-   Save a Game
-   Resume a Game
-   Make a play

## Installation

\$ npm install leito-mineswipper-rest-client

## Usages

```javascript
import Client from "leito-mineswipper-client";

const API_URL = "https://leito-minesweeper-api.herokuapp.com/";
const apiClient = new Client(API_URL);

// User login example
apiClient
    .login("username")
    .then((user) => {
        // some logic
    })
    .catch((error) => {
        // some exception handling
    });
```
