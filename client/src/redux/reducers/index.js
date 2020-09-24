import { combineReducers } from "redux";
import user from "redux/reducers/user";
import game from "redux/reducers/game";
import config from "redux/reducers/config";

export default combineReducers({ user, game, config });
