import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux";
import { useHistory } from "react-router-dom";

import { getSummary } from "redux/actions/user";
import { resumeGame } from "redux/actions/game";
import { getDateFormat, getTimeFormat } from "utils/dateTime";

const Home = ({ user, getSummary, resumeGame }) => {
    const history = useHistory();
    const { id: userId, summary } = user;

    useEffect(() => {
        getSummary(userId);
    }, [getSummary, userId]);

    const onResume = (e, id) => {
        e.preventDefault();
        resumeGame(id);
        history.push({
            pathname: "/game",
            gameId: id,
        });
    };

    const userUninishedGames = summary
        ? summary.unfinishedGames.map((game) => (
              <tr key={game.id}>
                  <td>
                      <a href="#resume" onClick={(e) => onResume(e, game.id)}>
                          <i className="glyphicon glyphicon-play"></i>
                      </a>
                  </td>
                  <td>{game.id}</td>
                  <td>
                      {game.rows} X {game.columns}{" "}
                      <span role="img" aria-label="Mine icon">
                          🧨
                      </span>
                      {game.mines}
                  </td>
                  <td>{getDateFormat(game.createdAt)}</td>
                  <td>{getTimeFormat(game.playedTime)}</td>
              </tr>
          ))
        : [];

    const tableUserUnfinishedGames = !!userUninishedGames && (
        <>
            <h4>Unfinished games</h4>
            <hr />
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th></th>
                        <th>Id</th>
                        <th>Details</th>
                        <th>Started date</th>
                        <th>Spent time</th>
                    </tr>
                </thead>
                <tbody>{userUninishedGames}</tbody>
            </table>
        </>
    );

    const userFinishedGames = summary
        ? summary.finishedGames.map((game) => (
              <tr key={game.id} className={game.won ? "success" : "danger"}>
                  <td>{game.id}</td>
                  <td>
                      {game.rows} X {game.columns}{" "}
                      <span role="img" aria-label="Mine icon">
                          🧨
                      </span>
                      {game.mines}
                  </td>
                  <td>{getDateFormat(game.createdAt)}</td>
                  <td>{getTimeFormat(game.playedTime)}</td>
              </tr>
          ))
        : [];

    const tableUserFinishedGames = !!userFinishedGames && (
        <>
            <h4>Finished games</h4>
            <hr />
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Details</th>
                        <th>Started date</th>
                        <th>Spent time</th>
                    </tr>
                </thead>
                <tbody>{userFinishedGames}</tbody>
            </table>
        </>
    );

    return (
        <div className="home-component">
            <div className="jumbotron text-center">
                <div className="row">
                    <div className="col-xs-12">
                        <h2 className="big-title big-title-primary">
                            Welcome to the Game
                        </h2>
                        <h6>Click the button to start a new game</h6>
                        <br />
                        <NavLink to="/game" className="btn btn-primary btn-lg">
                            <i className="glyphicon glyphicon-refresh"></i> New
                            game
                        </NavLink>
                    </div>
                    <div className="col-xs-12 col-sm-6">
                        <br />
                        {tableUserUnfinishedGames}
                    </div>
                    <div className="col-xs-12 col-sm-6">
                        <br />
                        {tableUserFinishedGames}
                    </div>
                </div>
            </div>
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
        getSummary: (id) => dispatch(getSummary(id)),
        resumeGame: (id) => dispatch(resumeGame(id)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Home);
