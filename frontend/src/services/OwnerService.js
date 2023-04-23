import axios from 'axios';

const OWNER_API_BASE_URL = '/owners';

class OwnerService {

    getOwners() {
        return axios.get(OWNER_API_BASE_URL);
    }

    getUrl() {
        return OWNER_API_BASE_URL;
    }
}

export default new OwnerService();