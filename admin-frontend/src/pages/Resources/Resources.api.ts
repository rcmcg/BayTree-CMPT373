import Api from "./api";
import { Resource } from "./types";

const getResourcesList = async() =>  {
    const response = await Api.get<Resource[]>('/resource/get/all')
    return response.data;
}

export {getResourcesList};
