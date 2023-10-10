import './assets/main.css'
import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Importa el router

const app = createApp(App);
app.use(router); // Añade el router a tu aplicación
app.mount('#app');
