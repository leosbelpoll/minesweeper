import {
    LOGIN_USER_FETCH,
    LOGIN_USER_SUCCESS,
    LOGIN_USER_ERROR,
    LOGOUT_USER_FETCH,
    LOGOUT_USER_SUCCESS,
    LOGOUT_USER_ERROR,
    GET_SUMMARY_FETCH,
    GET_SUMMARY_SUCCESS,
    GET_SUMMARY_ERROR,
} from "redux/actions/actionTypes";

const initialState = {
    id: null,
    username: null,
    loading: false,
    error: null,
    summary: null,
};

export default function (state = initialState, { type, payload }) {
    switch (type) {
        case LOGIN_USER_FETCH: {
            return {
                ...state,
                username: null,
                loading: true,
                error: null,
                summary: null,
            };
        }
        case LOGIN_USER_SUCCESS: {
            return {
                ...state,
                id: payload.id,
                username: payload.username,
                loading: false,
                error: null,
                summary: null,
            };
        }
        case LOGIN_USER_ERROR: {
            return {
                ...state,
                username: null,
                loading: false,
                error: payload,
                summary: null,
            };
        }
        case LOGOUT_USER_FETCH: {
            return {
                ...state,
                loading: true,
                error: null,
            };
        }
        case LOGOUT_USER_SUCCESS: {
            return {
                ...initialState,
            };
        }
        case LOGOUT_USER_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
            };
        }
        case GET_SUMMARY_FETCH: {
            return {
                ...state,
                loading: true,
                error: null,
                summary: null,
            };
        }
        case GET_SUMMARY_SUCCESS: {
            return {
                ...state,
                summary: payload,
                loading: false,
                error: null,
            };
        }
        case GET_SUMMARY_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
                summary: null,
            };
        }
        default:
            return state;
    }
}
