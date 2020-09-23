const fetch = require("isomorphic-unfetch");
const querystring = require("querystring");

function LeitoMineswipperClient(basePath) {
    this.basePath =
        basePath[basePath.length - 1] !== "/"
            ? basePath
            : basePath.slice(0, -1);

    this.USERS_URL = "/users";
    this.GAMES_URL = "/games";

    /**
     *
     * @param {String} endpoint
     * @param {Object} queryParams
     * @param {Object} options
     */
    this.request = function (
        endpoint = "",
        queryParams,
        requestOptions = { method: "GET" }
    ) {
        const qs = queryParams ? "?" + querystring.stringify(queryParams) : "";
        let url = this.basePath + endpoint + qs;

        const headers = {
            Accept: "application/json",
            "Content-Type": "application/json",
        };

        const config = {
            ...requestOptions,
            headers,
        };

        const _parseJSON = (response) => {
            return response.text().then(function (text) {
                return text ? JSON.parse(text) : {};
            });
        };

        return fetch(url, config).then((res) => {
            if (res.ok) {
                return _parseJSON(res);
            }
            throw new Error(res);
        });
    };
}

module.exports = LeitoMineswipperClient;
