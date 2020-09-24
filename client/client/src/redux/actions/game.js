import Client from "leito-mineswipper-rest-client";

import { getEnv } from "utils/env";
import {
    START_GAME_FETCH,
    START_GAME_SUCCESS,
    START_GAME_ERROR,
    SAVE_GAME_FETCH,
    SAVE_GAME_SUCCESS,
    SAVE_GAME_ERROR,
    RESUME_GAME_FETCH,
    RESUME_GAME_SUCCESS,
    RESUME_GAME_ERROR,
    PLAY_FETCH,
    PLAY_SUCCESS,
    PLAY_ERROR,
} from "redux/actions/actionTypes";

const apiClient = new Client(getEnv("API_URL"));

export const startGameFetch = () => ({
    type: START_GAME_FETCH,
});

export const startGameSuccess = (game) => ({
    type: START_GAME_SUCCESS,
    payload: game,
});

export const startGameError = (error) => ({
    type: START_GAME_ERROR,
    payload: error,
});

export const startGame = (gameOptions) => {
    return (dispatch) => {
        dispatch(startGameFetch());
        apiClient
            .startGame(gameOptions)
            .then((game) => {
                dispatch(startGameSuccess(game));
            })
            .catch((error) => {
                dispatch(startGameError(error));
            });
    };
};

export const saveGameFetch = () => ({
    type: SAVE_GAME_FETCH,
});

export const saveGameSuccess = () => ({
    type: SAVE_GAME_SUCCESS,
});

export const saveGameError = (error) => ({
    type: SAVE_GAME_ERROR,
    payload: error,
});

export const saveGame = (id) => {
    return (dispatch) => {
        dispatch(saveGameFetch());
        apiClient
            .saveGame(id)
            .then(() => {
                dispatch(saveGameSuccess());
            })
            .catch((error) => {
                dispatch(saveGameError(error));
            });
    };
};

export const resumeGameFetch = () => ({
    type: RESUME_GAME_FETCH,
});

export const resumeGameSuccess = (board) => ({
    type: RESUME_GAME_SUCCESS,
    payload: board,
});

export const resumeGameError = (error) => ({
    type: RESUME_GAME_ERROR,
    payload: error,
});

export const resumeGame = (id) => {
    return (dispatch) => {
        dispatch(resumeGameFetch());
        apiClient
            .resumeGame(id)
            .then((board) => {
                dispatch(resumeGameSuccess(board));
            })
            .catch((error) => {
                dispatch(resumeGameError(error));
            });
    };
};

export const playFetch = () => ({
    type: PLAY_FETCH,
});

export const playSuccess = (board) => ({
    type: PLAY_SUCCESS,
    payload: board,
});

export const playError = (error) => ({
    type: PLAY_ERROR,
    payload: error,
});

export const play = (id, options) => {
    return (dispatch) => {
        dispatch(playFetch());
        apiClient
            .play(id, options)
            .then((board) => {
                dispatch(playSuccess(board));
            })
            .catch((error) => {
                dispatch(playError(error));
            });
    };
};
