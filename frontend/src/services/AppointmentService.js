import axios from 'axios';

const APPOINTMENT_API_BASE_URL = '/appointments';

class AppointmentService {

    getAppointments() {
        return axios.get(APPOINTMENT_API_BASE_URL);
    }

    getUrl() {
        return APPOINTMENT_API_BASE_URL;
    }
}

export default new AppointmentService();