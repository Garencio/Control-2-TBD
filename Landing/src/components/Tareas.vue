<template>
  <div>
    <button @click="handleLogout">Logout</button>
    <h2>Crear Tarea</h2>

    <form @submit.prevent="handleCreateTarea">
      <div>
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" v-model="titulo">
      </div>
      <div>
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" v-model="descripcion"></textarea>
      </div>
      <div>
        <label for="vencimiento">Fecha de Vencimiento:</label>
        <input type="date" id="vencimiento" v-model="vencimiento">
      </div>
      <button type="submit">Agregar Tarea</button>
    </form>

    <h2>Mis Tareas</h2>
    <ul>
      <li v-for="tarea in tareas" :key="tarea.id">
        {{ tarea.titulo }} - {{ tarea.descripcion }} - {{ tarea.vencimiento }} - {{ tarea.estado }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      titulo: '',
      descripcion: '',
      vencimiento: '',
      tareas: []
    };
  },
  async mounted() {
    await this.fetchTareas();
  },
  methods: {
    async handleCreateTarea() {
      try {
        const userId = localStorage.getItem('userId');
        if (!userId) {
          alert('Por favor, inicie sesión primero.');
          return;
        }
        const response = await axios.post('http://localhost:8086/api/tareas', {
          titulo: this.titulo,
          descripcion: this.descripcion,
          vencimiento: this.vencimiento,
          id_usuario: userId
        });

        if (response && response.data) {
          this.titulo = '';
          this.descripcion = '';
          this.vencimiento = '';
          await this.fetchTareas();
        } else {
          alert('Error al agregar la tarea.');
        }
      } catch (error) {
        alert('Error al agregar la tarea.');
      }
    },
    async fetchTareas() {
      try {
        const userId = localStorage.getItem('userId');
        if (!userId) {
          alert('Por favor, inicie sesión primero.');
          return;
        }
        const response = await axios.get(`http://localhost:8086/api/tareas/${userId}`);
        if (response && response.data) {
          this.tareas = response.data;
        }
      } catch (error) {
        alert('Error al obtener las tareas.');
      }
    },
    async handleLogout() {
      localStorage.removeItem('userId');
      this.$router.push('/login');
    }
  }
};
</script>
