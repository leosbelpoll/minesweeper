import React from "react";
import { connect } from "react-redux";
import {
    BrowserRouter as Router,
    Redirect,
    Route,
    Switch,
} from "react-router-dom";

import Login from "components/Login";

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
                <div className="container">
                    <Switch>
                        <Route
                            path="/login"
                            render={() => <Redirect to="/" />}
                            exact
                        />
                        <h1>Router working!</h1>
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
