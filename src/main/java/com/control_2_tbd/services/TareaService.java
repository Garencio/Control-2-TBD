package com.control_2_tbd.services;

import com.control_2_tbd.entities.TareaEntity;
import com.control_2_tbd.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/tareas")
public class TareaService {

    @Autowired
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    @GetMapping
    public String obtenerTodasLasTareas(HttpSession session, Model model){
        int idUsuario = (int) session.getAttribute("idUsuario");
        List<TareaEntity> tareas = tareaRepository.findAllByUsuario(idUsuario);
        model.addAttribute("tareas", tareas);
        return "tareas";
    }

    @GetMapping("/completadas")
    public List<TareaEntity> obtenerTareasCompletadas(@RequestParam int idUsuario){
        return tareaRepository.findAllCompletedByUsuario(idUsuario);
    }

    @PostMapping
    @ResponseBody
    public TareaEntity crearTarea(@ModelAttribute TareaEntity nuevaTarea, HttpSession session){
        Integer id_usuario = (Integer) session.getAttribute("idUsuario");
        if (id_usuario == null) {
            return null;
        }
        System.out.println(id_usuario);
        return tareaRepository.createTarea(nuevaTarea, id_usuario);
    }

    //@PutMapping("/{id}")
    @PostMapping("/actualizar")
    public String actualizarTarea(@ModelAttribute TareaEntity tareaActualizada) {
        tareaRepository.updateTarea(tareaActualizada);
        return "redirect:/tareas";
    }

    @GetMapping("/editar")
    public String editarTarea(@RequestParam int id, Model model) {
        TareaEntity tarea = tareaRepository.findById(id);
        if (tarea != null) {
            model.addAttribute("tarea", tarea);
            return "editarTarea";
        } else {
            return "redirect:/tareas";
        }
    }
    @PostMapping("/eliminar")
    public String eliminarTarea(@RequestParam int id){
        tareaRepository.deleteTarea(id);
        return "redirect:/tareas";
    }

}