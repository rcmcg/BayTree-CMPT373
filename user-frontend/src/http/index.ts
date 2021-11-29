import axios from 'axios'
import {AuthResponse} from "../model/AuthResponse";

export const API_URL = `http://localhost:8080`

const $api = axios.create({
    withCredentials: true,
    baseURL: API_URL
})

$api.interceptors.request.use((config:any) => {
    config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`
    return config;
})

$api.interceptors.response.use((config) => {
    return config;
}, async (error) => {
    const originalRequest = error.config;
    if(error.response.status == 401 && error.config && !error.config.retry) {
        try {
            const response = await axios.get<AuthResponse>(`${API_URL}/api/token/refresh`,
                {withCredentials: true});
            localStorage.setItem('token', response.data.accessToken);
            return $api.request(originalRequest);
        } catch (e){
            console.log("Not Authorized")
        }
    }
    throw error;
})

export default $api;