import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/UserLogin.vue';
import Register from '@/components/UserRegistration.vue';
import Tareas from "@/components/Tareas.vue";

const routes = [
    {
        path: '/',
        redirect: '/login'
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
    {
        path: '/tareas/:idUsuario',
        component: Tareas,
        name: 'tareas',
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem('userId') == to.params.idUsuario) {
                next();
            } else {
                alert('Acceso no autorizado.');
                next('/login');
            }
        }
    },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE_URL || '/'),
    routes
});

export default router;
