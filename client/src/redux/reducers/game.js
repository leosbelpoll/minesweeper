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
} from "redux/actions/actionsTypes";

const initialState = {
    id: null,
    intialDate: null,
    playedTime: null,
    rows: null,
    columns: null,
    mines: null,
    board: [],
    loading: false,
    error: null,
    won: false,
    lost: false,
};

export default function (state = initialState, { type, payload }) {
    switch (type) {
        case START_GAME_FETCH: {
            return {
                ...initialState,
                loading: true,
            };
        }
        case START_GAME_SUCCESS: {
            return {
                ...state,
                id: payload.id,
                rows: payload.rows,
                columns: payload.columns,
                mines: payload.mines,
                loading: false,
                error: null,
                playedTime: 0,
                won: false,
                lost: false,
            };
        }
        case START_GAME_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
            };
        }
        case SAVE_GAME_FETCH: {
            return {
                ...initialState,
                id: state.id,
                loading: true,
                error: null,
            };
        }
        case SAVE_GAME_SUCCESS: {
            return {
                ...state,
                loading: false,
                error: null,
            };
        }
        case SAVE_GAME_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
            };
        }
        case RESUME_GAME_FETCH: {
            return {
                ...initialState,
                loading: true,
                error: null,
            };
        }
        case RESUME_GAME_SUCCESS: {
            return {
                ...state,
                id: payload.id,
                rows: payload.rows,
                columns: payload.columns,
                mines: payload.mines,
                board: payload.cells,
                playedTime: payload.playedTime,
                loading: false,
                error: null,
            };
        }
        case RESUME_GAME_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
            };
        }
        case PLAY_FETCH: {
            return {
                ...state,
                loading: true,
                error: null,
            };
        }
        case PLAY_SUCCESS: {
            return {
                ...state,
                board: [
                    ...state.board.filter(
                        (cell) =>
                            !payload.cells.find(
                                (newCell) =>
                                    newCell.row === cell.row &&
                                    newCell.column === cell.column
                            )
                    ),
                    ...payload.cells,
                ],
                won: payload.status === "WON",
                lost: payload.status === "LOST",
                loading: false,
                error: null,
            };
        }
        case PLAY_ERROR: {
            return {
                ...state,
                loading: false,
                error: payload,
                board: [],
            };
        }
        default:
            return state;
    }
}
