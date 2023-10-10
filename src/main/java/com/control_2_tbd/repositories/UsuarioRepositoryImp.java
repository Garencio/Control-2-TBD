package com.control_2_tbd.repositories;

import com.control_2_tbd.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public UsuarioEntity createUsuario(UsuarioEntity usuario) {
        try (Connection connection = sql2o.open()) {
            int id = (int) connection.createQuery("INSERT INTO usuario(nickname, contrasena) VALUES (:nickname, :contrasena)",true)
                    .addParameter("nickname", usuario.getNickname())
                    .addParameter("contrasena", usuario.getContrasena())  // Corregido aquí
                    .executeUpdate().getKey();
            usuario.setId(id);
            return usuario;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public UsuarioEntity findByUsuarioYContraseña(String nickname, String contrasena) {  // cambio de nombre de parámetro aquí
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM usuario WHERE nickname = :nickname AND contrasena = :contrasena")  // cambio de nombre de parámetro aquí
                    .addParameter("nickname", nickname)
                    .addParameter("contrasena", contrasena)  // cambio de nombre de parámetro aquí
                    .executeAndFetchFirst(UsuarioEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
