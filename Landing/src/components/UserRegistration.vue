<template>
  <div>
    <h2>Register</h2>
    <form @submit.prevent="handleRegister">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username">
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password">
      </div>
      <div>
        <label for="passwordConfirm">Confirm Password:</label>
        <input type="password" id="passwordConfirm" v-model="passwordConfirm">
      </div>
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script>

import api from "@/api";
export default {
  data() {
    return {
      username: '',
      password: '',
      passwordConfirm: ''
    };
  },
  methods: {
    async handleRegister() {
      // Validaciones básicas
      if (!this.username || !this.password) {
        alert("Username and password are required!");
        return;
      }

      if (this.password.length < 8) {
        alert("Password should be at least 8 characters long!");
        return;
      }

      if (this.password !== this.passwordConfirm) {
        alert("Passwords do not match!");
        return;
      }

      // Llamada al backend
      try {
        const response = await api.post('/register', {
          nickname: this.username,
          contrasena: this.password
        });

        const newUser = response.data;
        alert("Registered successfully with ID: " + newUser.id);
      } catch (error) {
        const errorMessage = error.response && error.response.data && error.response.data.message
            ? error.response.data.message
            : "An unknown error occurred.";
        alert("Error registering: " + errorMessage);
      }
    }
  }
};
</script>


<style scoped>
/* Aquí puedes agregar estilos específicos para el componente */
</style>
