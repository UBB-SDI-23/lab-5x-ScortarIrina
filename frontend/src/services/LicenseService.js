import axios from 'axios';

const LICENSE_API_BASE_URL = '/licenses';

class LicenseService {

    getLicenses() {
        return axios.get(LICENSE_API_BASE_URL);
    }

    getUrl() {
        return LICENSE_API_BASE_URL;
    }
}

export default new LicenseService();