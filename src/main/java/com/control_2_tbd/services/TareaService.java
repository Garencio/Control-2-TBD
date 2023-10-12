package com.control_2_tbd.services;

import com.control_2_tbd.entities.TareaEntity;
import com.control_2_tbd.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tareas")
public class TareaService {

    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    // Obtener todas las tareas de un usuario específico
    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<TareaEntity>> obtenerTodasLasTareas(@PathVariable int idUsuario) {
        List<TareaEntity> tareas = tareaRepository.findAllByUsuario(idUsuario);
        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/{idTarea}/completar")
    public ResponseEntity<TareaEntity> completarTarea(@PathVariable int idTarea) {
        Optional<TareaEntity> optionalTarea = Optional.ofNullable(tareaRepository.findById(idTarea));
        if (optionalTarea.isPresent()) {
            TareaEntity tarea = optionalTarea.get();
            tarea.setEstado("Completada");
            tareaRepository.save(tarea);
            return ResponseEntity.ok(tarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Obtener tareas completadas de un usuario específico
    @GetMapping("/{idUsuario}/completadas")
    public ResponseEntity<List<TareaEntity>> obtenerTareasCompletadas(@PathVariable int idUsuario) {
        List<TareaEntity> tareas = tareaRepository.findAllCompletedByUsuario(idUsuario);
        return ResponseEntity.ok(tareas);
    }

    // Crear una nueva tarea
    @PostMapping("/{idUsuario}")
    public ResponseEntity<TareaEntity> crearTarea(@PathVariable int idUsuario, @RequestBody TareaEntity nuevaTarea) {
        if (idUsuario <= 0) {  // Puedes tener otras validaciones según tus necesidades.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        TareaEntity tarea = tareaRepository.createTarea(nuevaTarea, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
    }


    // Actualizar una tarea
    @PutMapping("/{idTarea}")
    public ResponseEntity<TareaEntity> actualizarTarea(@PathVariable int idTarea, @RequestBody TareaEntity tareaActualizada) {
        TareaEntity tarea = tareaRepository.updateTarea(tareaActualizada);
        if (tarea == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tarea);
    }

    // Obtener una tarea específica para editar
    @GetMapping("/editar/{idTarea}")
    public ResponseEntity<TareaEntity> editarTarea(@PathVariable int idTarea) {
        TareaEntity tarea = tareaRepository.findById(idTarea);
        if (tarea == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tarea);
    }

    // Eliminar una tarea
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable int idTarea) {
        tareaRepository.deleteTarea(idTarea);
        return ResponseEntity.noContent().build();
    }

}