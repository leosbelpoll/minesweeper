import Client from "leito-mineswipper-rest-client";

import { getEnv } from "utils/env";
import {
    LOGIN_USER_FETCH,
    LOGIN_USER_SUCCESS,
    LOGIN_USER_ERROR,
    LOGOUT_USER_SUCCESS,
    GET_SUMMARY_FETCH,
    GET_SUMMARY_SUCCESS,
    GET_SUMMARY_ERROR,
} from "redux/actions/actionsTypes";

const apiClient = new Client(getEnv("API_URL"));

export const loginUserFetch = () => ({
    type: LOGIN_USER_FETCH,
});

export const loginUserSuccess = (user) => ({
    type: LOGIN_USER_SUCCESS,
    payload: user,
});

export const loginUserError = (error) => ({
    type: LOGIN_USER_ERROR,
    payload: error,
});

export const login = (username) => {
    return (dispatch) => {
        dispatch(loginUserFetch());
        apiClient
            .login(username)
            .then((user) => {
                dispatch(loginUserSuccess(user));
            })
            .catch((error) => {
                dispatch(loginUserError(error));
            });
    };
};

export const getSummaryFetch = () => ({
    type: GET_SUMMARY_FETCH,
});

export const getSummarySuccess = (summary) => ({
    type: GET_SUMMARY_SUCCESS,
    payload: summary,
});

export const getSummaryError = (error) => ({
    type: GET_SUMMARY_ERROR,
    payload: error,
});

export const getSummary = (id) => {
    return (dispatch) => {
        dispatch(getSummaryFetch());
        apiClient
            .getUserSummary(id)
            .then((summary) => {
                dispatch(getSummarySuccess(summary));
            })
            .catch((error) => {
                dispatch(getSummaryError(error));
            });
    };
};

export const logout = () => ({
    type: LOGOUT_USER_SUCCESS,
});
