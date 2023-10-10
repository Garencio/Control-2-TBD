
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
        const response = await axios.post('http://localhost:8086/api/login', {
          nickname: this.username,
          contrasena: this.password
        });

        console.log(response.data);

        if (response && response.data) {

          localStorage.setItem('userId', response.data);
          alert('Inicio de sesión exitoso!');
          console.log(this.$router);
          this.$router.push('/tareas');

        } else {
          alert('Credenciales inválidas. Por favor, intenta nuevamente.');
        }

      } catch (error) {
        alert('Error al iniciar sesión. Por favor, intenta nuevamente.');
      }
    }
  }
};
</script>

<style scoped>
/* Aquí puedes agregar estilos específicos para el componente */
</style>
