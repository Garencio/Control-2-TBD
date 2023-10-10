
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
/* Estilización para el componente */

div {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  margin-bottom: 1rem;
  text-align: center;
}

label {
  margin-bottom: 0.3rem;
  font-weight: 500;
}

input {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  font-size: 1rem;
  outline: none;
}

input:focus {
  border-color: #007BFF;
}

button {
  width: 100%;
  padding: 0.5rem 0;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

</style>