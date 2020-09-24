import { SET_CONFIG } from "redux/actions/actionTypes";

export const setConfig = (config) => ({
    type: SET_CONFIG,
    payload: config,
});
