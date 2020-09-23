package com.leito.minesweeper;

import com.leito.minesweeper.controller.GameController;
import com.leito.minesweeper.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private UserController userController;

    @Autowired
    private GameController gameController;

    @Test
    public void userControllerLoad() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void gameControllerLoad() {
        assertThat(gameController).isNotNull();
    }
}
