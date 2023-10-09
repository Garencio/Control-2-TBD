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

    @GetMapping("/")
    public String index(){
        return "redirect:/login";
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
    public String register(@RequestBody UsuarioEntity nuevoUsuario){
        usuarioRepository.createUsuario(nuevoUsuario);
        return  "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioEntity usuario){
        usuarioRepository.findByUsuarioYContraseña(usuario.getNickname(), usuario.getContraseña());
        return  "redirect:/";
    }
}
