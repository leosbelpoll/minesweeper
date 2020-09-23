const LeitoMinesWeeperClient = require("./index");
const nock = require("nock");

const FAKE_APi_URL = "http://localhost:8080/v1";
const USERS_URL = "/users";
const GAMES_URL = "/games";

const apiClient = new LeitoMinesWeeperClient(FAKE_APi_URL);

test("login", async () => {
    const mockResponse = {
        id: 1,
        username: "test@gmail.com",
    };
    nock(FAKE_APi_URL).post(USERS_URL).reply(200, mockResponse);

    const res = await apiClient.login("test@gmail.com");
    expect(res).toEqual(mockResponse);
});

test("start game", async () => {
    const mockResponse = {
        id: 1,
        rows: 10,
        columns: 10,
        mines: 10,
        createdAt: 99999,
    };
    nock(FAKE_APi_URL)
        .post(GAMES_URL + "/start")
        .reply(200, mockResponse);

    const res = await apiClient.startGame({
        rows: 10,
        columns: 10,
        mines: 10,
        userId: 1,
    });
    expect(res).toEqual(mockResponse);
});
