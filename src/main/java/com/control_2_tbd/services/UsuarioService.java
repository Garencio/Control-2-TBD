package com.control_2_tbd.services;

import com.control_2_tbd.entities.UsuarioEntity;
import com.control_2_tbd.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "*") // Permite cualquier origen. En producción, restringe esto a tu dominio específico.
@RestController
@RequestMapping("/api")

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
    public UsuarioEntity register(@RequestBody UsuarioEntity nuevoUsuario){
        return usuarioRepository.createUsuario(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity usuarioAutenticado = usuarioRepository.findByUsuarioYContraseña(usuario.getNickname(), usuario.getContrasena());
        if (usuarioAutenticado != null) {
            return ResponseEntity.ok(usuarioAutenticado.getId());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }


}