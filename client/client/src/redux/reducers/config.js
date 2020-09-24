import { SET_CONFIG } from "redux/actions/actionTypes";

const initialState = {
    rows: 10,
    columns: 10,
    mines: 10,
};

export default function (state = initialState, { type, payload }) {
    switch (type) {
        case SET_CONFIG: {
            return {
                ...state,
                rows: payload.rows,
                columns: payload.columns,
                mines: payload.mines,
            };
        }
        default:
            return state;
    }
}
