import Api from "./api";

const getResourcesList = async() =>  {
    const response = await Api.get('/user/get/mentors/all')
    return response.data;
}

export {getResourcesList};