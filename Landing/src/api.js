import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

axios.defaults.headers.common['Content-Type'] = 'application/json';

export default api;
