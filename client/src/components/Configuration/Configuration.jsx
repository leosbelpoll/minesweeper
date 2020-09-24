import React, { useState } from "react";
import { connect } from "react-redux";

import { setConfig } from "redux/actions/config";

import "./Configuration.scss";

const Configuration = ({
    config: { rows, columns, mines },
    setConfigurations,
}) => {
    const [rowsNumber, setRowsNumber] = useState(rows);
    const [success, setSuccess] = useState(false);
    const [error, setError] = useState(false);
    const [columnsNumber, setColumnsNumber] = useState(columns);
    const [minesNumber, setMinesNumber] = useState(mines);

    const onSetConfig = (e) => {
        e.preventDefault();
        if (minesNumber > rowsNumber * columnsNumber) {
            setError(true);
            setSuccess(false);
        } else {
            setError(false);
            setSuccess(true);
            setConfigurations({
                rows: rowsNumber,
                columns: columnsNumber,
                mines: minesNumber,
            });
        }
    };

    return (
        <div className="configuration-component">
            <div className="jumbotron">
                <h2 className="big-title big-title-primary">
                    Game configuration
                </h2>
                <div className="row">
                    <div className="col-xs-12">
                        <form onSubmit={onSetConfig}>
                            <div className="row">
                                <div className="col-sm-3 col-md-4"></div>
                                <div className="col-xs-12 col-sm-6 col-md-4">
                                    <label htmlFor="rows">
                                        Enter rows number
                                    </label>
                                    <input
                                        type="number"
                                        id="rowsNumber"
                                        name="rowsNumber"
                                        value={rowsNumber}
                                        placeholder="Rows number"
                                        className="form-control"
                                        onChange={(e) =>
                                            setRowsNumber(e.target.value)
                                        }
                                    />
                                </div>
                                <div className="col-sm-3 col-md-4"></div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-sm-3 col-md-4"></div>
                                <div className="col-xs-12 col-sm-6 col-md-4">
                                    <label htmlFor="columns">
                                        Enter columns number
                                    </label>
                                    <input
                                        type="number"
                                        id="columnsNumber"
                                        name="columnsNumber"
                                        value={columnsNumber}
                                        placeholder="Columns number"
                                        className="form-control"
                                        onChange={(e) =>
                                            setColumnsNumber(e.target.value)
                                        }
                                    />
                                </div>
                                <div className="col-sm-3 col-md-4"></div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-sm-3 col-md-4"></div>
                                <div className="col-xs-12 col-sm-6 col-md-4">
                                    <label htmlFor="mines">
                                        Enter mines number
                                    </label>
                                    <input
                                        type="number"
                                        id="minesNumber"
                                        name="minesNumber"
                                        value={minesNumber}
                                        placeholder="Mines number"
                                        className="form-control"
                                        onChange={(e) =>
                                            setMinesNumber(e.target.value)
                                        }
                                    />
                                </div>
                                <div className="col-sm-3 col-md-4"></div>
                            </div>
                            <br />
                            <div className="row">
                                <div className="col-sm-3 col-md-4"></div>
                                <div className="col-xs-12 col-sm-6 col-md-4">
                                    <button
                                        type="submit"
                                        className="btn btn-primary btn-lg btn-full"
                                    >
                                        <i className="glyphicon glyphicon-ok"></i>{" "}
                                        Config
                                    </button>
                                    {success && (
                                        <>
                                            <br />
                                            <br />
                                            <span className="text-success">
                                                Configuration setted
                                                successfully!
                                            </span>
                                        </>
                                    )}
                                    {error && (
                                        <>
                                            <br />
                                            <br />
                                            <span className="text-danger">
                                                Mines could not be greather than{" "}
                                                {rowsNumber * columnsNumber}
                                            </span>
                                        </>
                                    )}
                                </div>
                                <div className="col-sm-3 col-md-4"></div>
                            </div>
                        </form>
                    </div>
                </div>
                <br />
            </div>
        </div>
    );
};

const mapStateToProps = (state) => {
    return {
        config: state.config,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        setConfigurations: (options) => dispatch(setConfig(options)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Configuration);
