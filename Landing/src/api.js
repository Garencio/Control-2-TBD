import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080'  // Ajusta esto a la dirección de tu backend
});

export default api;
