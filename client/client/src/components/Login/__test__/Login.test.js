import React from "react";
import { Provider } from "react-redux";
import ReactDOM from "react-dom";
import { render } from "@testing-library/react";
import configureStore from "redux-mock-store";

import Login from "../index";
import mockedStore from "./mockedStore";

const mockStore = configureStore([]);
let store;

beforeEach(() => {
    store = mockStore({
        ...mockedStore,
    });
});

it("renders without crashing", () => {
    ReactDOM.render(
        <Provider store={store}>
            <Login />
        </Provider>,
        document.createElement("div")
    );
});

test("renders closed Cell", () => {
    const cell = {
        row: 0,
        column: 0,
    };
    const { getByTestId } = render(
        <Provider store={store}>
            <Login {...cell} />
        </Provider>
    );
    const welcomeText = getByTestId("welcome-text");
    expect(welcomeText).toHaveTextContent("Welcome to");
});
