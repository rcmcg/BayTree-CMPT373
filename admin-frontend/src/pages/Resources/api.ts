import axios, {Axios, AxiosInstance, AxiosResponse} from "axios";
import {Mentor} from '../AddMentor/UsersContextProvider';

class Api{
    private static axiosInstance: AxiosInstance;

    static init(){
        this.axiosInstance = axios.create({
            baseURL: 'http://localhost:8080'
        })
    }

    static async get<ResponseType>(url: string){
        return await Api.axiosInstance.get<ResponseType>(url);
    }

    static async post<ResponseType, DataType>(url: string, data?:DataType){
        return Api.axiosInstance.post<DataType,AxiosResponse<ResponseType>>(url, data);
    }
}

export default Api;