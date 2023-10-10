package com.control_2_tbd.services;

import com.control_2_tbd.entities.UsuarioEntity;
import com.control_2_tbd.repositories.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String mostrarRegister() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public UsuarioEntity register(@ModelAttribute UsuarioEntity nuevoUsuario){
        return usuarioRepository.createUsuario(nuevoUsuario);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsuarioEntity usuario, HttpSession session) {
        UsuarioEntity usuarioAutenticado = usuarioRepository.findByUsuarioYContraseña(usuario.getNickname(), usuario.getContrasena());
        if (usuarioAutenticado != null) {
            session.setAttribute("idUsuario", usuarioAutenticado.getId());  // Guardar el id del usuario en la sesión
            return "redirect:/tareas";
        }
        return "login";
    }

}