package com.control_2_tbd.repositories;

import com.control_2_tbd.entities.TareaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public TareaEntity createTarea(TareaEntity tarea, Integer id_usuario) {
        try (Connection connection = sql2o.open()) {
            int id = (int) connection.createQuery("INSERT INTO tarea(titulo, descripcion, vencimiento, estado, id_usuario) VALUES (:titulo, :descripcion, :vencimiento, :estado, :id_usuario)", true)
                    .addParameter("titulo", tarea.getTitulo())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("vencimiento", tarea.getVencimiento())
                    .addParameter("estado", "Pendiente")
                    .addParameter("id_usuario", id_usuario)  // Asegúrate de que el usuario esté establecido en la tarea
                    .executeUpdate()
                    .getKey();
            tarea.setId(id);
            return tarea;
        } catch (Exception e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TareaEntity updateTarea(TareaEntity tarea) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("UPDATE tarea SET titulo = :titulo, descripcion = :descripcion, vencimiento = :vencimiento, estado = :estado WHERE id = :id")
                    .bind(tarea)
                    .executeUpdate();
            return tarea;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteTarea(int id) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("DELETE FROM tarea WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<TareaEntity> findAllByUsuario(int idUsuario) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM tarea WHERE id_usuario = :id_usuario")
                    .addParameter("id_usuario", idUsuario)
                    .executeAndFetch(TareaEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<TareaEntity> findAllCompletedByUsuario(int idUsuario) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM tarea WHERE id_usuario = :id_usuario AND estado = 'completada'")
                    .addParameter("id_usuario", idUsuario)
                    .executeAndFetch(TareaEntity.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
