import React from "react";
import { connect } from "react-redux";
import { NavLink } from "react-router-dom";

import { logout } from "redux/actions/user";
import { getEnv } from "utils/env";

import "./Header.scss";

const Header = (props) => {
    const logout = (e) => {
        e.preventDefault();
        props.logout();
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
                <a href="#logout" className="logout" onClick={logout}>
                    <i className="glyphicon glyphicon-off"></i> Logout
                </a>
            </nav>
        </div>
    );
};

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => dispatch(logout()),
    };
};

export default connect(null, mapDispatchToProps)(Header);
