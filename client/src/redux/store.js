import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import { composeWithDevTools } from "redux-devtools-extension";
import throttle from "lodash.throttle";

import { loadReduxState, saveReduxState } from "utils/localStorage";
import rootReducer from "redux/reducers";

const persistedReduxState = loadReduxState();

const store = createStore(
    rootReducer,
    persistedReduxState,
    composeWithDevTools(applyMiddleware(thunk))
);

store.subscribe(
    throttle(() => {
        saveReduxState({
            ...store.getState(),
        });
    }, 1000)
);

export default store;
