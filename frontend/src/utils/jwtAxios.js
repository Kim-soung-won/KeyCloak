import axios from 'axios';
import { jsonHeader, url } from './single';

const jwtAxios = axios.create({
    baseURL: url,
    withCredentials: true,
    headers: jsonHeader
});


const getPersistedState = () => {
    try {
        const serializedState = localStorage.getItem("access_token");
        if (serializedState === null) {
            return undefined;
        }
        return serializedState;
    } catch (err) {
        console.error("Failed to retrieve persisted state from localStorage:", err);
        return undefined;
    }
};


jwtAxios.interceptors.request.use(
    (config) => {
        let persistedState = getPersistedState();
        let token = persistedState;
        console.log("token : ", token);
        if (token) {
            config.headers["Authorization"] = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default jwtAxios;