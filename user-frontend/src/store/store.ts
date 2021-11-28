import {makeAutoObservable} from 'mobx';
import AuthService from "../service/AuthService";

export default class Store {
    isAuth = false;

    constructor() {
        makeAutoObservable(this);
    }

    setAuth(bool: boolean){
        this.isAuth = bool;
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

}