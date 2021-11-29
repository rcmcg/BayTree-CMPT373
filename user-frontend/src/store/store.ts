import {makeAutoObservable} from 'mobx';
import AuthService from "../service/AuthService";
import axios from 'axios'
import {AuthResponse} from "../model/AuthResponse";
import {API_URL} from "../http";

export default class Store {
    isAuth = false;
    isLoading =false;

    constructor() {
        makeAutoObservable(this);
    }

    setAuth(bool: boolean){
        this.isAuth = bool;
    }

    setLoading(bool:boolean){
        this.isLoading = bool;
    }

    async login(username: string, password: string) {
        try {
            const response = await AuthService.login(username, password);
            localStorage.setItem('token', response.data.accessToken);
            this.setAuth(true);
        } catch (e:any) {
            console.log(e.response?.data?.message)
        }
    }

    async checkAuth() {
        this.setLoading(true);
        try{
            const response = await axios.get<AuthResponse>(`${API_URL}/api/token/refresh`,
                {withCredentials: true})
            localStorage.setItem('token', response.data.accessToken);
            this.setAuth(true);
        }catch (e:any) {
            console.log(e.response?.data?.message)
        }finally {
            this.setLoading(false);
        }
    }

}