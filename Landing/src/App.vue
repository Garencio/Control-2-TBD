<template>
  <div id="app">
    <header>
      <h1>Mi Aplicación</h1>
      <nav>
        <router-link to="/login">Iniciar Sesión</router-link>
        <router-link to="/register">Registrarse</router-link>
      </nav>
    </header>

    <main>
      <router-view></router-view> <!-- Aquí es donde se renderizarán tus componentes de las rutas -->
    </main>
  </div>
</template>

<script>
import Login from './components/UserLogin.vue';
import Register from './components/UserRegistration.vue';

export default {
  name: 'App',
  components: {
    Login,
    Register
  }
};
</script>
<style scoped>
/* Estilos generales para tu App.vue */
header {
  background-color: #f5f5f5;
  padding: 1rem 0;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

main {
  padding: 1rem;
}

nav {
  margin-top: 1rem;
}

router-link {
  margin: 0 1rem;
  text-decoration: none;
  color: #007BFF;
  cursor: pointer;
}

router-link:hover {
  text-decoration: underline;
}

</style>