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

    <h2>Mis Tareas Pendientes</h2>
    <ul v-if="tareas && tareas.length">
      <li v-for="tarea in tareas.filter(t => t.estado === 'Pendiente')" :key="tarea.id">
        {{ tarea.titulo }} - {{ tarea.descripcion }}
        <button @click="handleEditTarea(tarea.id)">Editar</button>
        <button @click="handleCompleteTarea(tarea.id)">Completar</button>
        <button @click="handleDeleteTarea(tarea.id)">Eliminar</button>
      </li>
    </ul>

    <h2>Tareas Completadas</h2>
    <ul v-if="tareas && tareas.length">
      <li v-for="tarea in tareas.filter(t => t.estado === 'Completada')" :key="tarea.id">
        {{ tarea.titulo }} - {{ tarea.descripcion }}
      </li>
    </ul>
    <p v-else>No hay tareas disponibles.</p>


    <section v-if="editing">
      <h2>Editar Tarea</h2>
      <form @submit.prevent="handleUpdateTarea">
        <div>
          <label for="editTitulo">Título:</label>
          <input type="text" id="editTitulo" v-model="editTitulo">
        </div>
        <div>
          <label for="editDescripcion">Descripción:</label>
          <textarea id="editDescripcion" v-model="editDescripcion"></textarea>
        </div>
        <div>
          <label for="editVencimiento">Fecha de Vencimiento:</label>
          <input type="date" id="editVencimiento" v-model="editVencimiento">
        </div>
        <button type="submit">Actualizar Tarea</button>
      </form>
    </section>
    <!-- ... -->
  </div>

</template>

<script>
import axios from 'axios';

export default {
  name: 'TareasComponent',

  data() {
    return {
      titulo: '',
      descripcion: '',
      vencimiento: '',
      tareas: [],
      editTitulo: '',
      editDescripcion: '',
      editVencimiento: '',
      editing: false,
      currentTareaId: null

    };
  },

  async mounted() {
    await this.fetchTareas();
  },

  methods: {
    async handleCreateTarea() {
      try {
        const userId = parseInt(localStorage.getItem('userId'));

        if (!userId) {
          alert('Por favor, inicie sesión primero.');
          return;
        }
        const response = await axios.post(`http://localhost:8080/api/tareas/${userId}`, {
          titulo: this.titulo,
          descripcion: this.descripcion,
          vencimiento: this.vencimiento
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
        const userId = this.$route.params.idUsuario; // Obtiene el ID del usuario desde la URL
        const response = await axios.get(`http://localhost:8080/api/tareas/${userId}`);

        // Aquí añades el console.log para ver la respuesta:
        console.log(response.data);

        if (response && response.data) {
          this.tareas = response.data;
        }
      } catch (error) {
        alert('Error al obtener las tareas.');
      }
    },
    async handleCompleteTarea(id) {
      try {
        const response = await axios.put(`http://localhost:8080/api/tareas/${id}/completar`, {
          estado: 'Completada'
        });
        if (response && response.data) {
          await this.fetchTareas();
        } else {
          alert('Error al completar la tarea.');
        }
      } catch (error) {
        alert('Error al completar la tarea.');
      }
    },


    async handleLogout() {
      localStorage.removeItem('userId');
      this.$router.push('/login');
    },

    async handleEditTarea(id) {
      try {
        const response = await axios.get(`http://localhost:8080/api/tareas/editar/${id}`);
        if (response && response.data) {
          this.editTitulo = response.data.titulo;
          this.editDescripcion = response.data.descripcion;
          this.editVencimiento = response.data.vencimiento;
          this.currentTareaId = id;
          this.editing = true;
        }
      } catch (error) {
        alert('Error al obtener datos de la tarea.');
      }
    },

    async handleUpdateTarea() {
      try {
        const updatedTarea = {
          titulo: this.editTitulo,
          descripcion: this.editDescripcion,
          vencimiento: this.editVencimiento
          // Puedes agregar otros campos si los necesitas
        };
        const response = await axios.put(`http://localhost:8080/api/tareas/${this.currentTareaId}`, updatedTarea);
        if (response && response.data) {
          this.editing = false;
          this.editTitulo = '';
          this.editDescripcion = '';
          this.editVencimiento = '';
          this.currentTareaId = null;
          await this.fetchTareas();
        }
      } catch (error) {
        alert('Error al actualizar la tarea.');
      }
    },

    async handleDeleteTarea(id) {
      try {
        await axios.delete(`http://localhost:8080/api/tareas/${id}`);
        await this.fetchTareas();
      } catch (error) {
        alert('Error al eliminar la tarea.');
      }
    }
  }
};
</script>
