package com.control_2_tbd.services;

import com.control_2_tbd.entities.UsuarioEntity;
import com.control_2_tbd.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioEntity> register(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity user = usuarioRepository.createUsuario(usuario);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioEntity> login(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity user = usuarioRepository.findByUsuarioYContraseña(usuario.getNickname(), usuario.getContrasena());
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(user);
    }



}