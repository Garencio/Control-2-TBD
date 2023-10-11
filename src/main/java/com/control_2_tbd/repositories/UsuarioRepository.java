package com.control_2_tbd.repositories;

import com.control_2_tbd.entities.UsuarioEntity;

public interface UsuarioRepository {

    public UsuarioEntity createUsuario(UsuarioEntity usuario);
    public UsuarioEntity findByUsuarioYContraseña(String usuario, String contraseña);


}
