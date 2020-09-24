import React from "react";
import { connect } from "react-redux";
import {
    BrowserRouter as Router,
    Redirect,
    Route,
    Switch,
} from "react-router-dom";

import Login from "components/Login";
import Home from "components/Home";
import Header from "components/Header";
import Configuration from "components/Configuration";
import Game from "components/Game";
import PageNotFound from "components/PageNotFound";

const Routes = ({ logged }) => {
    if (!logged) {
        return (
            <Router>
                <Redirect to="/login" />
                <Route path="/login" component={Login} exact />
            </Router>
        );
    }

    return (
        <div className="app-component">
            <Router>
                <Header />
                <div className="container">
                    <Switch>
                        <Route
                            path="/login"
                            render={() => <Redirect to="/" />}
                            exact
                        />
                        <Route path="/" component={Home} exact />
                        <Route
                            path="/configuration"
                            component={Configuration}
                            exact
                        />
                        <Route path="/game" component={Game} exact />
                        <Route component={PageNotFound} />
                    </Switch>
                </div>
            </Router>
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        logged: !!state.user.id,
    };
};

export default connect(mapStateToProps)(Routes);
