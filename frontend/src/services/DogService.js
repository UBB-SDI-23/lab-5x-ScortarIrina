import axios from 'axios';

const DOG_API_BASE_URL = '/dogs';

class DogService {

    getDogs() {
        return axios.get(DOG_API_BASE_URL);
    }

    getUrl() {
        return DOG_API_BASE_URL;
    }
}

export default new DogService();