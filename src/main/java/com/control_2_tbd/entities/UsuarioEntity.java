package com.control_2_tbd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    private int id;
    private String nickname;
    private String contrasena;

}