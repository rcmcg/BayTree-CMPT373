import Api from "./api";
import { Resource } from "./types";

const getResourcesList = async() =>  {
    const response = await Api.get<Resource[]>('/resource/get/all')
    return response.data;
}

const createResource = async (resource:Resource): Promise<Resource> => {
    const {data} = await Api.post<Resource, Resource>('/resource/add');
    return data;
}


const deleteResource = async (resourceId: number) => {
    const {data} = await Api.delete<Resource[]>(`/resource/delete/${resourceId}`);
    return data
  }

export {
    getResourcesList,
    createResource,
    deleteResource,
 };
