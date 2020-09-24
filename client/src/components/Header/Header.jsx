import React from "react";
import { connect } from "react-redux";
import { NavLink } from "react-router-dom";

import { logout } from "redux/actions/user";
import { getEnv } from "utils/env";

import "./Header.scss";

const Header = ({ logoutUser, game, user, config }) => {
    const loading = user.loading || config.loading || game.loading;

    const logout = (e) => {
        e.preventDefault();
        logoutUser();
    };

    return (
        <div className="header-component">
            <nav>
                <span className="title">
                    <NavLink to="/">{getEnv("APP_NAME")}</NavLink>
                </span>
                <br className="visible-xs" />
                <NavLink to="/">
                    <i className="glyphicon glyphicon-home"></i> Home
                </NavLink>
                <NavLink to="/configuration">
                    <i className="glyphicon glyphicon-cog"></i> Configuration
                </NavLink>
                {loading && <span className="text-warning">Loading ....</span>}
                <a href="#logout" className="logout" onClick={logout}>
                    <i className="glyphicon glyphicon-off"></i> Logout
                </a>
            </nav>
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        user: state.user,
        config: state.config,
        game: state.game,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        logoutUser: () => dispatch(logout()),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);
