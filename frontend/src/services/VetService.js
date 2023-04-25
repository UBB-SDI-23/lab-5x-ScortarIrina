import axios from 'axios';

const VET_API_BASE_URL = '/vets';

class VetService {

    getVets() {
        return axios.get(VET_API_BASE_URL);
    }

    getUrl() {
        return VET_API_BASE_URL;
    }
}

export default new VetService();