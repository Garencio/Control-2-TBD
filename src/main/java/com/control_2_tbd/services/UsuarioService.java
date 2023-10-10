package com.control_2_tbd.services;

import com.control_2_tbd.entities.UsuarioEntity;
import com.control_2_tbd.repositories.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
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
    @ResponseBody
    public UsuarioEntity login(@ModelAttribute UsuarioEntity usuario){
        return usuarioRepository.findByUsuarioYContraseña(usuario.getNickname(), usuario.getContrasena());
    }
}

