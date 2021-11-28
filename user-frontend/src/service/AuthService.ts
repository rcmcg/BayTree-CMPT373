import $api from "../http";
import {AxiosResponse} from 'axios'
import {AuthResponse} from "../model/AuthResponse";

export default class AuthService {
    static async login(username: string, password: string): Promise<AxiosResponse<AuthResponse>> {
        return $api.post('/api/login', {username, password})
    }

    // static async logout(): Promise<void> {
    //     return $api.post('/api/logout')
    // }
}