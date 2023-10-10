import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/UserLogin.vue';
import Register from '@/components/UserRegistration.vue';

const routes = [
    {
        path: '/',
        redirect: '/login'  // Esto redireccionará automáticamente la raíz a /login
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    // otras rutas...
];


const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE_URL || '/'),
    routes
});

export default router;
