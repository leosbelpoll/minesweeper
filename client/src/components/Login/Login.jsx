import React, { useState } from "react";
import { connect } from "react-redux";

import { login } from "redux/actions/user";
import "./Login.scss";

const Login = ({ login, user }) => {
    const [username, setUsername] = useState();
    const { loading, error } = user;

    const onSubmit = (e) => {
        e.preventDefault();
        login(username);
    };

    const onChange = (e) => {
        setUsername(e.target.value);
    };

    return (
        <div className="login">
            <h2 data-testid="welcome-text" className="big-title">
                Welcome to Leito's minesweeper!
            </h2>
            <form onSubmit={onSubmit} className="text-center">
                <input
                    type="email"
                    id="username"
                    name="username"
                    placeholder="user@example.com ↩"
                    onChange={onChange}
                    required
                />
            </form>
            {loading && <div className="text-center">Loading ....</div>}
            {error && (
                <div className="text-center">
                    <span class="label label-danger">
                        Oops, an error ocurred!
                    </span>
                </div>
            )}
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        user: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        login: (username) => dispatch(login(username)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
