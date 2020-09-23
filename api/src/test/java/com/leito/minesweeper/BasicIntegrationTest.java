package com.leito.minesweeper;

import com.leito.minesweeper.dto.StartGameRequest;
import com.leito.minesweeper.dto.UserLoginRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BasicIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    public static void setup() {
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
    }

    @Test
    public void startGame() throws Exception {
        this.login();

        final String baseUrl = "http://localhost:" + port + "/v1/games/start";
        URI uri = new URI(baseUrl);
        StartGameRequest startGameRequest = new StartGameRequest(10, 10, 10, 1L);
        HttpEntity<StartGameRequest> request = new HttpEntity<>(startGameRequest, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody().startsWith("{\"id\":2,\"rows\":10,\"columns\":10,\"mines\":10,\"createdAt\":\""));
    }

    private void login() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/v1/users";
        URI uri = new URI(baseUrl);
        UserLoginRequest userLoginRequest = new UserLoginRequest("test@gmail.com");
        HttpEntity<UserLoginRequest> request = new HttpEntity<>(userLoginRequest, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("{\"id\":1,\"username\":\"test@gmail.com\"}", result.getBody());
    }
}
