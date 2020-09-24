const STORAGE_KEY = "leito-minesweeper-redux-state";

export const loadReduxState = () => {
    const serializedReduxState = localStorage.getItem(STORAGE_KEY);
    if (serializedReduxState === null) {
        return undefined;
    }
    return JSON.parse(serializedReduxState);
};

export const saveReduxState = (reduxState) => {
    const serializedReduxState = JSON.stringify({
        ...reduxState,
    });
    localStorage.setItem(STORAGE_KEY, serializedReduxState);
};
