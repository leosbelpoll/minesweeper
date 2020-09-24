import React from "react";
import { NavLink } from "react-router-dom";

const PageNotFound = () => {
    return (
        <div className="page-not-found-component">
            <div className="jumbotron text-center">
                <h2 className="big-title big-title-error">Sorry</h2>
                <h3>
                    <i className="glyphicon glyphicon-exclamation-sign"></i> The
                    page you are looking for doesn't exist
                </h3>
                <h6>Click the button to start a new game</h6>
                <br />
                <NavLink to="/game" className="btn btn-primary btn-lg">
                    <i className="glyphicon glyphicon-play"></i> New game
                </NavLink>
            </div>
        </div>
    );
};

export default PageNotFound;
