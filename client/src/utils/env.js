import { DEFAULT_VALUES } from "../configs";

export const getEnv = (envVariable) => {
    return (
        process.env[`REACT_APP_${envVariable}`] ||
        DEFAULT_VALUES[`DEFAULT_${envVariable}`]
    );
};
