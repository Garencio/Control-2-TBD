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
            int id = (int) connection.createQuery("INSERT INTO usuario(nickname, contraseña) VALUES (:nickname, :contraseña)",true)
                    .addParameter("usuario", usuario.getNickname())
                    .addParameter("contraseña", usuario.getContraseña())
                    .executeUpdate().getKey();
            usuario.setId(id);
            return usuario;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public UsuarioEntity findByUsuarioYContraseña(String nickname, String contraseña) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM usuario WHERE nickname = :nickname AND contraseña = :contraseña")
                    .addParameter("nickname", nickname)
                    .addParameter("contraseña", contraseña)
                    .executeAndFetchFirst(UsuarioEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
