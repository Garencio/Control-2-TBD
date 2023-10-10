
<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username">
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password">
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8080/login', {
          nickname: this.username,
          contrasena: this.password
        });

        if (response && response.data) {
          // Si la autenticación es exitosa, puedes almacenar la información del usuario
          // en el almacenamiento local o en una tienda Vuex, por ejemplo.
          // Por ahora, solo mostraremos una alerta indicando éxito.

          alert('Inicio de sesión exitoso!');
          // Aquí puedes redirigir al usuario a otra página o realizar otras acciones.

        } else {
          alert('Credenciales inválidas. Por favor, intenta nuevamente.');
        }

      } catch (error) {
        // Manejo de errores, por ejemplo si el backend no está disponible o si devuelve un error.
        alert('Error al iniciar sesión. Por favor, intenta nuevamente.');
      }
    }
  }
};
</script>

<style scoped>
/* Aquí puedes agregar estilos específicos para el componente */
</style>
