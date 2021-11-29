import $api from "../http";
import {AxiosResponse} from 'axios'
import {AuthResponse} from "../model/AuthResponse";

export default class AuthService {
    static async login(username: string, password: string): Promise<AxiosResponse<AuthResponse>> {
        const params = new URLSearchParams();
        params.append('username', `${username}`);
        params.append('password', `${password}`);
        return $api.post('/api/login', params)
    }

    // static async logout(): Promise<void> {
    //     return $api.post('/api/logout')
    // }
}