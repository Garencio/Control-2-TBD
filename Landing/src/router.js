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
        path: '/tareas',
        component: Tareas,
        beforeEnter: (to, from, next) => {
            console.log('Checking auth:', localStorage.getItem('userId'));
            if (localStorage.getItem('userId')) {
                console.log('Authorized!');
                next();
            } else {
                console.log('Unauthorized, redirecting to login.');
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
