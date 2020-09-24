import { SET_CONFIG } from "redux/actions/actionsTypes";

export const setConfig = (config) => ({
    type: SET_CONFIG,
    payload: config,
});
