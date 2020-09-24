export const getDateFormat = (millisenconds) => {
    const date = new Date(millisenconds);
    let day = date.getDate();

    let month = date.getMonth() + 1;
    const year = date.getFullYear();
    if (day < 10) {
        day = `0${day}`;
    }

    if (month < 10) {
        month = `0${month}`;
    }
    return `${year}/${month}/${day}`;
};

export const getTimeFormat = (millisenconds) => {
    let seconds = Math.floor((millisenconds / 1000) % 60),
        minutes = Math.floor((millisenconds / (1000 * 60)) % 60),
        hours = Math.floor((millisenconds / (1000 * 60 * 60)) % 24);

    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    return `${hours}:${minutes}:${seconds}`;
};
