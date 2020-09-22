package com.leito.minesweeper.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leito.minesweeper.game.Board;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class BoardJsonConverter implements AttributeConverter<Board, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Board board) {
        try {
            return objectMapper.writeValueAsString(board);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert to Json", e);
        }
    }

    @Override
    public Board convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, Board.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert from Json", e);
        }
    }
}
