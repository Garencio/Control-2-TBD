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
/* Estilización para el componente de registro */

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
